package com.pizza.awesomepizza.repository;

import com.pizza.awesomepizza.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    boolean existsByCodeAndOrderDateGreaterThan(String code, LocalDateTime today);

    Optional<Order> findByCodeAndOrderDateGreaterThan(String code, LocalDateTime today);

    List<Order> findAllByOrderDateGreaterThan(LocalDateTime today);

}
