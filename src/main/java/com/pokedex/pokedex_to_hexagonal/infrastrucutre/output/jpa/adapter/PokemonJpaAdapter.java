package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.adapter;

import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.domain.spi.IPokemonPersistencePort;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.NoDataFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.PokemonAlreadyExistsException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.PokemonNotFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.entity.PokemonEntity;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.mapper.PokemonEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.repository.IPokemonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PokemonJpaAdapter implements IPokemonPersistencePort {

    private final IPokemonRepository pokemonRepository;

    private final PokemonEntityMapper pokemonEntityMapper;

    @Override
    public void savePokemon(Pokemon pokemon) {
        if (pokemonRepository.findByNumber(pokemon.getNumber()).isPresent()) {
            throw new PokemonAlreadyExistsException();
        }
        pokemonRepository.save(pokemonEntityMapper.toEntity(pokemon));
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        List<PokemonEntity> pokemonEntityList = pokemonRepository.findAll();
        if (pokemonEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return pokemonEntityMapper.toPokemonList(pokemonEntityList);
    }

    @Override
    public Pokemon getPokemon(Long pokemonNumber) {

        return pokemonEntityMapper.toPokemon(pokemonRepository.findByNumber(pokemonNumber)
                .orElseThrow(PokemonNotFoundException::new));
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemonEntityMapper.toEntity(pokemon));
    }

    @Override
    public void deletePokemon(Long pokemonNumber) {
        pokemonRepository.deleteByNumber(pokemonNumber);
    }
}