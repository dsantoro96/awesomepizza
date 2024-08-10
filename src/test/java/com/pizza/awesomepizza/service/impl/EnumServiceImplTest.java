package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.AwesomePizzaApplication;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import com.pizza.awesomepizza.enumeration.ProductCategory;
import com.pizza.awesomepizza.exceptions.BadRequestException;
import com.pizza.awesomepizza.service.EnumService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {AwesomePizzaApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnumServiceImplTest {

    @Autowired
    private EnumService service;

    @BeforeAll
    public void setUp() {
    }

    @AfterAll
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void getEnums() {

        List<OrderStatus> orderStatusList = service.getEnums("OrderStatus");
        List<ProductCategory> productCategoryList = service.getEnums("ProductCategory");

        assertFalse(orderStatusList.isEmpty());
        assertFalse(productCategoryList.isEmpty());

        assertThrows(BadRequestException.class, () -> service.getEnums("somethingElse"));

    }

}