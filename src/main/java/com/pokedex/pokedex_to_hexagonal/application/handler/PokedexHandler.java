package com.pokedex.pokedex_to_hexagonal.application.handler;

import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexRequestDto;
import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexResponseDto;
import com.pokedex.pokedex_to_hexagonal.application.mapper.PokedexRequestDtoMapper;
import com.pokedex.pokedex_to_hexagonal.application.mapper.PokedexResponseDtoMapper;
import com.pokedex.pokedex_to_hexagonal.application.mapper.TypeDtoMapper;
import com.pokedex.pokedex_to_hexagonal.domain.api.IPhotoServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.api.IPokemonServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.api.ITypeServicePort;
import com.pokedex.pokedex_to_hexagonal.domain.model.Photo;
import com.pokedex.pokedex_to_hexagonal.domain.model.Pokemon;
import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PokedexHandler implements IPokedexHandler
{

    private final IPokemonServicePort pokemonServicePort;
    private final ITypeServicePort typeServicePort;
    private final IPhotoServicePort photoServicePort;
    private final PokedexRequestDtoMapper pokedexRequestDtoMapper;
    private final PokedexResponseDtoMapper pokedexResponseDtoMapper;
    private final TypeDtoMapper typeDtoMapper;

    @Override
    public void savePokemonInPokedex(PokedexRequestDto pokedexRequestDto) {
        Type type = typeServicePort.saveType(pokedexRequestDtoMapper.toType(pokedexRequestDto));
        Photo photo = photoServicePort.savePhoto(pokedexRequestDtoMapper.toPhoto(pokedexRequestDto));
        Pokemon pokemon = pokedexRequestDtoMapper.toPokemon(pokedexRequestDto);
        pokemon.setTypeId(type.getId());
        pokemon.setPhotoId(photo.getId());
        pokemonServicePort.savePokemon(pokemon);
    }

    @Override
    public List<PokedexResponseDto> getAllPokemonFromPokedex() {
        return pokedexResponseDtoMapper.
                toResponseList(
                        pokemonServicePort.getAllPokemon(),
                        typeServicePort.getAllTypes(),
                        photoServicePort.getAllPhotos());
    }

    @Override
    public PokedexResponseDto getPokemonFromPokedex(Long pokemonNumber) {
        Pokemon pokemon = pokemonServicePort.getPokemon(pokemonNumber);
        return pokedexResponseDtoMapper.toResponse(pokemon, typeDtoMapper.toDto(typeServicePort.getType(pokemon.getTypeId())), photoServicePort.getPhoto(pokemon.getPhotoId()));
    }

    @Override
    public void updatePokemonInPokedex(PokedexRequestDto pokedexRequestDto) {
        Pokemon oldPokemon = pokemonServicePort.getPokemon(pokedexRequestDto.getNumber());

        Type newType = pokedexRequestDtoMapper.toType(pokedexRequestDto);
        newType.setId(oldPokemon.getTypeId());
        typeServicePort.updateType(newType);

        Photo newPhoto = pokedexRequestDtoMapper.toPhoto(pokedexRequestDto);
        newPhoto.setId(oldPokemon.getPhotoId());
        photoServicePort.updatePhoto(newPhoto);

        Pokemon newPokemon = pokedexRequestDtoMapper.toPokemon(pokedexRequestDto);
        newPokemon.setId(oldPokemon.getId());
        newPokemon.setTypeId(oldPokemon.getTypeId());
        newPokemon.setPhotoId(oldPokemon.getPhotoId());
        pokemonServicePort.updatePokemon(newPokemon);
    }

    @Override
    public void deletePokemonFromPokedex(Long pokemonNumber) {
        Pokemon pokemon = pokemonServicePort.getPokemon(pokemonNumber);
        typeServicePort.deleteType(pokemon.getTypeId());
        photoServicePort.deletePhoto(pokemon.getPhotoId());
        pokemonServicePort.deletePokemon(pokemonNumber);
    }
}
