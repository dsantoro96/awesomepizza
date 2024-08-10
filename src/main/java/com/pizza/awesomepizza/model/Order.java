package com.pizza.awesomepizza.model;

import com.pizza.awesomepizza.annotation.NullNotBlank;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<Product> products = new ArrayList<>();

    private OrderStatus status;

    private LocalDateTime orderDate;

}
