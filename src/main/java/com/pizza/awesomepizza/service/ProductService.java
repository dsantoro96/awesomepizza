package com.pizza.awesomepizza.service;

import com.pizza.awesomepizza.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProduct(String id);

    Product updateProduct(String id, Product product);

    void deleteProduct(String id);

    List<Product> getProducts();

}
