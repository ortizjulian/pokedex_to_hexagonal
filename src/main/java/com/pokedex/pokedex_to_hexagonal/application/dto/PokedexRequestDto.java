package com.pokedex.pokedex_to_hexagonal.application.dto;

import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokedexRequestDto
{
    private Long number;
    private String name;
    private Type types;
    private String photo;
}
