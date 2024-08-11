package com.pizza.awesomepizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("fileItems")
@NoArgsConstructor
@AllArgsConstructor
public class FileItem {

    @Id
    private String id;

    private String fileId;

    private String name;

    private long sizeInBytes;

    private String Size;

    private boolean temporary;

}
