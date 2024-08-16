package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.repository;

import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPhotoRepository extends MongoRepository<PhotoEntity, String> {}