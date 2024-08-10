package com.pizza.awesomepizza.dto;

import com.pizza.awesomepizza.enumeration.EnumTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumTemplateDTO {

    private String name;

    private Object value;

    public static EnumTemplateDTO valueOf(EnumTemplate<?> e) {
        return new EnumTemplateDTO(e.name(), e.value());
    }

}
