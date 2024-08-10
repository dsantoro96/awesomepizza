package com.pizza.awesomepizza.model;

import com.pizza.awesomepizza.annotation.NullNotBlank;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Document("order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @NullNotBlank
    private String id;

    @NullNotBlank
    private String code;

    @NotEmpty
    private Map<String, @NotNull @Min(1) Integer> products = new HashMap<>();

    private OrderStatus status;

    private LocalDateTime orderDate;

}
