package com.pizza.awesomepizza.mapper;

import com.pizza.awesomepizza.dto.ProductDTO;
import com.pizza.awesomepizza.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper extends ModelMapper<Product, ProductDTO> {
}
