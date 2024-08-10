package com.pizza.awesomepizza.mapper;

import com.pizza.awesomepizza.dto.OrderDTO;
import com.pizza.awesomepizza.model.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper extends ModelMapper<Order, OrderDTO> {
}
