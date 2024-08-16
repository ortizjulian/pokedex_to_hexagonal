package com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.adapter;

import com.pokedex.pokedex_to_hexagonal.domain.model.Photo;
import com.pokedex.pokedex_to_hexagonal.domain.spi.IPhotoPersistencePort;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.NoDataFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.exception.PhotoNotFoundException;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.entity.PhotoEntity;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.mapper.PhotoEntityMapper;
import com.pokedex.pokedex_to_hexagonal.infrastrucutre.output.mongodb.repository.IPhotoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PhotoMongodbAdapter implements IPhotoPersistencePort {

    private final IPhotoRepository photoRepository;
    private final PhotoEntityMapper photoEntityMapper;

    @Override
    public Photo savePhoto(Photo photo) {
        return photoEntityMapper.toPhoto(photoRepository.save(photoEntityMapper.toEntity(photo)));
    }

    @Override
    public List<Photo> getAllPhotos() {
        List<PhotoEntity> photoEntityList = photoRepository.findAll();
        if (photoEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return photoEntityMapper.toPhotoList(photoEntityList);
    }

    @Override
    public Photo getPhoto(String photoId) {
        return photoEntityMapper.toPhoto(photoRepository.findById(photoId).orElseThrow(PhotoNotFoundException::new));
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoRepository.save(photoEntityMapper.toEntity(photo));
    }

    @Override
    public void deletePhoto(String photoId) {
        photoRepository.deleteById(photoId);
    }
}
