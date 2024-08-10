package com.pizza.awesomepizza.rest;

import com.pizza.awesomepizza.dto.EnumTemplateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/enum")
public interface EnumController {

    /**
     * Retrieves a list of EnumTemplateDTO based on the provided template name.
     *
     * @param template the name of the template to retrieve the enums for
     * @return a ResponseEntity containing a list of EnumTemplateDTO
     */
    @GetMapping("/{template}")
    ResponseEntity<List<EnumTemplateDTO>> getEnums(@PathVariable("template") String template);

}
