package com.pizza.awesomepizza.service.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.pizza.awesomepizza.exceptions.InternalServerErrorException;
import com.pizza.awesomepizza.exceptions.NotFoundException;
import com.pizza.awesomepizza.exceptions.UnsupportedMediaTypeException;
import com.pizza.awesomepizza.model.FileItem;
import com.pizza.awesomepizza.repository.FileRepository;
import com.pizza.awesomepizza.service.FileItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileItemServiceImpl implements FileItemService {

    private final FileRepository fileRepository;
    private final GridFsTemplate gridFsTemplate;

    @Override
    public FileItem uploadFile(MultipartFile file) {
        try {
            if (isImage(file)) {
                FileItem newFileItem = new FileItem();

                ObjectId fileId = gridFsTemplate.store(
                        file.getInputStream(),
                        file.getOriginalFilename(),
                        file.getContentType()
                );

                newFileItem.setName(file.getOriginalFilename());
                newFileItem.setSizeInBytes(file.getSize());
                newFileItem.setSize(bytesToHumanReadable(file.getSize()));
                newFileItem.setTemporary(true);
                newFileItem.setFileId(fileId.toString());

                return fileRepository.save(newFileItem);
            }

            throw new UnsupportedMediaTypeException("Unsupported Media Type: " + file.getContentType());
        } catch (IOException e) {
            throw new InternalServerErrorException(e);
        }
    }

    @Override
    public GridFsResource downloadFile(String id) {
        FileItem fileItem = fileRepository.findById(id).orElseThrow(NotFoundException::new);

        Optional<GridFSFile> opt = getGridFSFile(fileItem.getFileId());

        if (opt.isEmpty()) {
            throw new InternalServerErrorException("Could not download file with id: " + id);
        }

        GridFSFile gridFSFile = opt.get();

        return gridFsTemplate.getResource(gridFSFile);
    }

    @Override
    public FileItem markFile(String id, boolean temporary) {
        FileItem fileItem = fileRepository.findById(id).orElseThrow(NotFoundException::new);

        fileItem.setTemporary(temporary);

        return fileRepository.save(fileItem);
    }

    @Override
    public void deleteTemporaryFiles() {
        List<FileItem> fileItems = fileRepository.findAllByTemporary(true);

        List<String> fileIds = fileItems.stream().map(FileItem::getFileId).toList();

        Query query = getIdsQuery(fileIds);
        gridFsTemplate.delete(query);

        fileRepository.deleteAll(fileItems);
    }

    @Override
    public String bytesToHumanReadable(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            return sizeInBytes + " B";
        }

        String[] units = {"B", "KB", "MB", "GB"};
        int unitIndex = 0;

        double size = sizeInBytes;
        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format("%.1f %s", size, units[unitIndex]);
    }

    private Optional<GridFSFile> getGridFSFile(String id) {
        Query query = getIdQuery(id);

        return Optional.ofNullable(gridFsTemplate.findOne(query));
    }

    private Query getIdQuery(String id) {
        Criteria criteria = Criteria.where("_id").is(id);

        return Query.query(criteria);
    }

    private Query getIdsQuery(List<String> fileIds) {
        Criteria criteria = Criteria.where("_id").in(fileIds);

        return Query.query(criteria);
    }

    private boolean isImage(MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).contains("image");
    }

}
