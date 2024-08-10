package com.pizza.awesomepizza.rest.impl;

import com.pizza.awesomepizza.dto.EnumTemplateDTO;
import com.pizza.awesomepizza.enumeration.EnumTemplate;
import com.pizza.awesomepizza.rest.EnumController;
import com.pizza.awesomepizza.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EnumControllerImpl implements EnumController {

    private final EnumService enumService;

    @Override
    public ResponseEntity<List<EnumTemplateDTO>> getEnums(@PathVariable("template") String template) {
        List<EnumTemplate<?>> enums = enumService.getEnums(template);

        return ResponseEntity.ok(enums.stream().map(EnumTemplateDTO::valueOf).toList());
    }

}
