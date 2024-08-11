package com.pizza.awesomepizza.service;

import com.pizza.awesomepizza.model.FileItem;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileItem uploadFile(MultipartFile file);

    GridFsResource downloadFile(String id);

    FileItem markFile(String id, boolean temporary);

    void deleteTemporaryFiles();

    String bytesToHumanReadable(long sizeInBytes);

}
