package com.pizza.awesomepizza.mapper;

import com.pizza.awesomepizza.dto.OrderDetailDTO;
import com.pizza.awesomepizza.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDetailMapper extends ModelMapper<Order, OrderDetailDTO> {

    @Mapping(target = "products", ignore = true)
    Order toModel(OrderDetailDTO dto);

    @Mapping(target = "products", ignore = true)
    OrderDetailDTO toDto(Order model);

}
