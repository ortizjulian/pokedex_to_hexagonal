package com.pokedex.pokedex_to_hexagonal.application.handler;

import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexRequestDto;
import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexResponseDto;

import java.util.List;

public interface IPokedexHandler
{
    void savePokemonInPokedex(PokedexRequestDto pokedexRequestDto);

    List<PokedexResponseDto> getAllPokemonFromPokedex();

    PokedexResponseDto getPokemonFromPokedex(Long pokemonNumber);

    void updatePokemonInPokedex(PokedexRequestDto pokedexRequestDto);

    void deletePokemonFromPokedex(Long pokemonNumber);
}
