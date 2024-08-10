package com.pizza.awesomepizza.mapper;

import com.pizza.awesomepizza.dto.OrderDTO;
import com.pizza.awesomepizza.model.Order;
import com.pizza.awesomepizza.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = ProductMapper.class)
public interface OrderMapper extends ModelMapper<Order, OrderDTO> {

    @Mapping(source = "products", target = "totalPrice", qualifiedByName = "getTotalPrice")
    OrderDTO toDto(Order order);

    @Named("getTotalPrice")
    default double getTotalPrice(List<Product> products) {
        return products.stream().map(Product::getPrice).reduce(0.0, Double::sum);
    }

}
