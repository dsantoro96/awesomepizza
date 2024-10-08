package com.pizza.awesomepizza.dto;

import com.pizza.awesomepizza.annotation.NullNotBlank;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    @NullNotBlank
    private String id;

    @NullNotBlank
    private String code;

    private List<ProductDTO> products = new ArrayList<>();

    private OrderStatus status;

    private LocalDateTime orderDate;

    private double totalPrice;

}
