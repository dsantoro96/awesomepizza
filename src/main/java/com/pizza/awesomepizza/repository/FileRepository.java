package com.pizza.awesomepizza.repository;

import com.pizza.awesomepizza.model.FileItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<FileItem, String> {

    List<FileItem> findAllByTemporary(boolean temporary);

}
