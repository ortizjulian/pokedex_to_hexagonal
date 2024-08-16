package com.pokedex.pokedex_to_hexagonal.application.mapper;

import com.pokedex.pokedex_to_hexagonal.application.dto.TypeDto;
import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TypeDtoMapper {

    TypeDto toDto(Type type);
}
