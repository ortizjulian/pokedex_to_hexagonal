package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.mapper;


import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.entity.PokemonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PokemonEntityMapper {

    PokemonEntity toEntity(Pokemon pokemon);

    Pokemon toPokemon(PokemonEntity pokemonEntity);

    List<Pokemon> toPokemonList(List<PokemonEntity> pokemonEntityList);
}