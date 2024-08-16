package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private String name;
    private Long typeId;
    private String photoId;
}