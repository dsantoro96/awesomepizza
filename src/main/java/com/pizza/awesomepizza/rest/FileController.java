package com.pizza.awesomepizza.rest;

import com.pizza.awesomepizza.dto.FileDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/files")
public interface FileController {

    @PostMapping
    ResponseEntity<FileDTO> uploadFile(@RequestPart("file") MultipartFile file);

    @GetMapping("/{id}")
    ResponseEntity<Resource> downloadFile(@PathVariable String id) throws IOException;

    @PutMapping("/{id}")
    ResponseEntity<FileDTO> markFile(@PathVariable String id, @RequestParam boolean temporary);

    @DeleteMapping
    ResponseEntity<Void> deleteTemporaryFiles();

}
