package com.pokedex.pokedex_to_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokedexResponseDto
{
    private Long number;
    private String name;
    private TypeDto types;
    private String photo;
}
