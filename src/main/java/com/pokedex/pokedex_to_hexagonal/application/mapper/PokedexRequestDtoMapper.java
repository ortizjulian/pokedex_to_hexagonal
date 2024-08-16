package com.pokedex.pokedex_to_hexagonal.application.mapper;

import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexRequestDto;
import com.pokedex.pokedex_to_hexagonal.domain.model.Photo;
import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PokedexRequestDtoMapper {

    Pokemon toPokemon(PokedexRequestDto pokedexRequestDto);

    @Mapping(source = "pokedexRequest.types.firstType", target = "firstType")
    @Mapping(source = "pokedexRequest.types.secondType", target = "secondType")
    Type toType(PokedexRequestDto pokedexRequestDto);

    @Mapping(target = "photo", qualifiedByName = "base64ToByteArray")
    Photo toPhoto(PokedexRequestDto pokedexRequestDto);

    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String base64Photo) {
        return Base64.getDecoder().decode(base64Photo);
    }
}