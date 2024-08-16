package com.pokedex.pokedex_to_hexagonal.infrastrucutre.configuration;

import com.pokedex.pokedex_to_hexagonal.domain.api.IPhotoServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.api.IPokemonServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.api.ITypeServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.spi.IPhotoPersistencePort;
import com.pokedex.pokedex_to_hexagonal.domain.spi.IPokemonPersistencePort;
import com.pokedex.pokedex_to_hexagonal.domain.spi.ITypePersistencePort;
import com.pokedex.pokedex_to_hexagonal.domain.usecase.PhotoUseCase;
import com.pokedex.pokedex_to_hexagonal.domain.usecase.PokemonUseCase;
import com.pokedex.pokedex_to_hexagonal.domain.usecase.TypeUseCase;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.adapter.PokemonJpaAdapter;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.adapter.TypeJpaAdapter;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.mapper.PokemonEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.mapper.TypeEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.repository.IPokemonRepository;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.repository.ITypeRepository;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.adapter.PhotoMongodbAdapter;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.mapper.PhotoEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.repository.IPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPokemonRepository pokemonRepository;
    private final PokemonEntityMapper pokemonEntityMapper;
    private final ITypeRepository typeRepository;
    private final TypeEntityMapper typeEntityMapper;
    private final IPhotoRepository photoRepository;
    private final PhotoEntityMapper photoEntityMapper;

    @Bean
    public IPokemonPersistencePort pokemonPersistencePort() {
        return new PokemonJpaAdapter(pokemonRepository, pokemonEntityMapper);
    }

    @Bean
    public IPokemonServicePort pokemonServicePort() {
        return new PokemonUseCase(pokemonPersistencePort());
    }

    @Bean
    public ITypePersistencePort typePersistencePort() {
        return new TypeJpaAdapter(typeRepository, typeEntityMapper);
    }

    @Bean
    public ITypeServicePort typeServicePort() {
        return new TypeUseCase(typePersistencePort());
    }

    @Bean
    public IPhotoPersistencePort photoPersistencePort() {
        return new PhotoMongodbAdapter(photoRepository, photoEntityMapper);
    }

    @Bean
    public IPhotoServicePort photoServicePort() {
        return new PhotoUseCase(photoPersistencePort());
    }
}
