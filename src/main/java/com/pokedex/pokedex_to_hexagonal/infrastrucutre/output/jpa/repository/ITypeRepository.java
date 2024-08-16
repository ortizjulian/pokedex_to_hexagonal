package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.repository;

import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<TypeEntity, Long> {
}
