package com.pizza.awesomepizza.rest.impl;

import com.pizza.awesomepizza.dto.FileDTO;
import com.pizza.awesomepizza.mapper.FileMapper;
import com.pizza.awesomepizza.rest.FileController;
import com.pizza.awesomepizza.service.FileItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileControllerImpl implements FileController {

    private final FileItemService fileItemService;
    private final FileMapper fileMapper;

    @Override
    public ResponseEntity<FileDTO> uploadFile(MultipartFile file) {
        return ResponseEntity.ok(fileMapper.toDto(fileItemService.uploadFile(file)));
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String id) throws IOException {
        GridFsResource resource = fileItemService.downloadFile(id);

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.valueOf(resource.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<FileDTO> markFile(String id, boolean temporary) {
        return ResponseEntity.ok(fileMapper.toDto(fileItemService.markFile(id, temporary)));
    }

    @Override
    public ResponseEntity<Void> deleteTemporaryFiles() {
        fileItemService.deleteTemporaryFiles();

        return ResponseEntity.ok().build();
    }

}
