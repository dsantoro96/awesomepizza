package com.pizza.awesomepizza.service;

import com.pizza.awesomepizza.enumeration.EnumTemplate;

import java.util.List;

public interface EnumService {

    /**
     * Retrieves a list of enums based on the provided template name.
     *
     * @param template the name of the template to retrieve the enums for
     * @param <T> the type of EnumTemplate
     * @return a list of enums of type {@link T}
     */
    <T extends EnumTemplate<?>> List<T> getEnums(String template);

}
