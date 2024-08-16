package com.pokedex.pokedex_to_hexagonal.domain.spi;

import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;

import java.util.List;

public interface IPokemonPersistencePort {
    void savePokemon(Pokemon pokemon);

    List<Pokemon> getAllPokemon();

    Pokemon getPokemon(Long pokemonNumber);

    void updatePokemon(Pokemon pokemon);

    void deletePokemon(Long pokemonNumber);
}
