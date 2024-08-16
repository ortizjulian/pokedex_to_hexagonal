package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.adapter;

import com.pokedex.pokedex_to_hexagonal.domain.model.Type;
import com.pokedex.pokedex_to_hexagonal.domain.spi.ITypePersistencePort;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.NoDataFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.TypeNotFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.entity.TypeEntity;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.mapper.TypeEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.jpa.repository.ITypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TypeJpaAdapter implements ITypePersistencePort {
    private final ITypeRepository typeRepository;
    private final TypeEntityMapper typeEntityMapper;

    @Override
    public Type saveType(Type type) {
        return typeEntityMapper.toType(typeRepository.save(typeEntityMapper.toEntity(type)));
    }

    @Override
    public List<Type> getAllTypes() {
        List<TypeEntity> typeEntityList = typeRepository.findAll();
        if (typeEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return typeEntityMapper.toTypeList(typeEntityList);
    }

    @Override
    public Type getType(Long typeId) {
        return typeEntityMapper.toType(typeRepository.findById(typeId).orElseThrow(TypeNotFoundException::new));
    }

    @Override
    public void updateType(Type type) {
        typeRepository.save(typeEntityMapper.toEntity(type));
    }

    @Override
    public void deleteType(Long typeId) {
        typeRepository.deleteById(typeId);
    }
}