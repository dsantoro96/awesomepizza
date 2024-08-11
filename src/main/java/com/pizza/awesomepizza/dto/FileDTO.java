package com.pizza.awesomepizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private String id;

    private String fileId;

    private String name;

    private long sizeInBytes;

    private String Size;

    private boolean temporary;

}
