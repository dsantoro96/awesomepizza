package com.pizza.awesomepizza.dto;

import com.pizza.awesomepizza.annotation.NullNotBlank;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NullNotBlank
    private String id;

    @NullNotBlank
    private String code;

    @NotEmpty
    private Map<String, @NotNull @Min(1) Integer> products = new HashMap<>();

    private OrderStatus status;

    private LocalDateTime orderDate;

}
