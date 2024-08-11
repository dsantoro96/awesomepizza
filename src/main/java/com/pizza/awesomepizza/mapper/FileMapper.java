package com.pizza.awesomepizza.mapper;

import com.pizza.awesomepizza.dto.FileDTO;
import com.pizza.awesomepizza.model.FileItem;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper extends ModelMapper<FileItem, FileDTO> {
}
