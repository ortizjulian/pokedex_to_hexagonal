package com.pokedex.pokedex_to_hexagonal.domain.usecase;

import com.pokedex.pokedex_to_hexagonal.domain.api.IPokemonServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.domain.spi.IPokemonPersistencePort;

import java.util.List;

public class PokemonUseCase implements IPokemonServicePort {

    private final IPokemonPersistencePort iPokemonPersistencePort;

    public PokemonUseCase(IPokemonPersistencePort iPokemonPersistencePort) {
        this.iPokemonPersistencePort = iPokemonPersistencePort;
    }

    @Override
    public void savePokemon(Pokemon pokemon) {
        this.iPokemonPersistencePort.savePokemon(pokemon);
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        return this.iPokemonPersistencePort.getAllPokemon();
    }

    @Override
    public Pokemon getPokemon(Long pokemonNumber) {
        return this.getPokemon(pokemonNumber);
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
         this.updatePokemon(pokemon);
    }

    @Override
    public void deletePokemon(Long pokemonNumber) {
        this.iPokemonPersistencePort.deletePokemon(pokemonNumber);
    }
}
