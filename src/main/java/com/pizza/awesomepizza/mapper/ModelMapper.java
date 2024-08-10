package com.pizza.awesomepizza.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ModelMapper<Model, DTO> {

    Model toModel(DTO dto);

    DTO toDto(Model model);

    default List<Model> toModel(List<DTO> dtoList) {
        return dtoList.stream().map(this::toModel).collect(Collectors.toList());
    }

    default List<DTO> toDto(List<Model> modelList) {
        return modelList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
