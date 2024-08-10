package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.enumeration.EnumTemplate;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import com.pizza.awesomepizza.enumeration.ProductCategory;
import com.pizza.awesomepizza.exceptions.BadRequestException;
import com.pizza.awesomepizza.service.EnumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnumServiceImpl implements EnumService {

    @Override
    public <T extends EnumTemplate<?>> List<T> getEnums(String template) {
        Class<T> clazz = getEnumClass(template);

        return getEnumTemplateConstants(clazz);
    }

    @SuppressWarnings("unchecked")
    private <T extends EnumTemplate<?>> Class<T> getEnumClass(String template) {
        switch (template) {
            case "OrderStatus":
                return (Class<T>) OrderStatus.class;
            case "ProductCategory":
                return (Class<T>) ProductCategory.class;
            default:
                throw new BadRequestException(String.format("Unknown enum template '%s'", template));
        }
    }

    private <T extends EnumTemplate<?>> List<T> getEnumTemplateConstants(Class<T> clazz) {
        return List.of(clazz.getEnumConstants());
    }

}
