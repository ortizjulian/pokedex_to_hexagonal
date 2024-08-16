package com.pokedex.pokedex_to_hexagonal.application.mapper;

import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexResponseDto;
import com.pokedex.pokedex_to_hexagonal.application.dto.TypeDto;
import com.pokedex.pokedex_to_hexagonal.domain.model.Photo;
import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {TypeDtoMapper.class})
public interface PokedexResponseDtoMapper {

    TypeDtoMapper INSTANCE = Mappers.getMapper(TypeDtoMapper.class);

    @Mapping(target = "types.firstType", source = "typeDto.firstType")
    @Mapping(target = "types.secondType", source = "typeDto.secondType")
    @Mapping(target = "photo", qualifiedByName = "byteArrayToBase64")
    PokedexResponseDto toResponse(Pokemon pokemon, TypeDto typeDto, Photo photo);

    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArrayPhoto) {
        return Base64.getEncoder().encodeToString(byteArrayPhoto);
    }

    default List<PokedexResponseDto> toResponseList(List<Pokemon> pokemonList, List<Type> typeList, List<Photo> photoList) {
        return pokemonList.stream()
                .map(pokemon -> {
                    PokedexResponseDto pokedexResponseDto = new PokedexResponseDto();
                    pokedexResponseDto.setNumber(pokemon.getNumber());
                    pokedexResponseDto.setName(pokemon.getName());
                    pokedexResponseDto.setTypes(INSTANCE.toDto(typeList.stream().filter(type -> type.getId().equals(pokemon.getTypeId())).findFirst().orElse(null)));
                    pokedexResponseDto.setPhoto(byteArrayToBase64(photoList.stream().filter(photo -> photo.getId().equals(pokemon.getPhotoId())).findFirst().orElse(null).getPhoto()));
                    return pokedexResponseDto;
                }).toList();
    }
}