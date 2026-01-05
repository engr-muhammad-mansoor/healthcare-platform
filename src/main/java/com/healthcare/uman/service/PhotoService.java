package com.healthcare.uman.service;

import java.io.IOException;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.uman.model.user.Photo;
import com.healthcare.uman.repository.PhotoRepository;

@Service
public class PhotoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private PhotoRepository photoRepository;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        LOGGER.debug("addPhoto for the title : {}", title);
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepository.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(String id) {
        LOGGER.debug("getPhoto for the id : {}", id);
        final Optional<Photo> photoRepositoryById = photoRepository.findById(id);
        if (photoRepositoryById.isPresent()) {
            return photoRepositoryById.get();
        }
        return null;
    }
}
