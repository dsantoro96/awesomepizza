package com.pizza.awesomepizza.repository;

import com.pizza.awesomepizza.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, String id);

    boolean existsAllByIdIn(Collection<String> id);

}
