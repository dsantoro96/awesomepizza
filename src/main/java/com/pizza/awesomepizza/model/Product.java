package com.pizza.awesomepizza.model;

import com.pizza.awesomepizza.annotation.NullNotBlank;
import com.pizza.awesomepizza.enumeration.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @NullNotBlank
    private String id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<String> ingredients = new ArrayList<>();

    @Min(0)
    private double price;

    @NotNull
    private ProductCategory category;

}
