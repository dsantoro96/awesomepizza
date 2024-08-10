package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.AwesomePizzaApplication;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import com.pizza.awesomepizza.enumeration.ProductCategory;
import com.pizza.awesomepizza.exceptions.BadRequestException;
import com.pizza.awesomepizza.exceptions.NotFoundException;
import com.pizza.awesomepizza.model.Order;
import com.pizza.awesomepizza.model.Product;
import com.pizza.awesomepizza.repository.OrderRepository;
import com.pizza.awesomepizza.repository.ProductRepository;
import com.pizza.awesomepizza.service.OrderService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {AwesomePizzaApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    Product product1;
    Product product2;
    private String orderCode;

    @BeforeAll
    public void setUp() {
        Order yesterdayOrder = new Order();
        yesterdayOrder.setOrderDate(LocalDateTime.now(ZoneOffset.UTC).minusDays(1));

        product1 = new Product();
        product2 = new Product();

        product1.setName("Pizza Margherita");
        product1.setCategory(ProductCategory.PIZZAS);
        product1.setIngredients(List.of(
                "Pizza Dough",
                "Tomato Sauce",
                "Mozzarella",
                "Basil",
                "Olive Oil"
        ));
        product1.setPrice(4.5);

        product2.setName("Pizza Marinara");
        product2.setCategory(ProductCategory.PIZZAS);
        product2.setIngredients(List.of(
                "Pizza Dough",
                "Tomato Sauce",
                "Garlic",
                "Oregano",
                "Olive Oil"
        ));
        product2.setPrice(3.5);

        orderRepository.save(yesterdayOrder);

        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
    }

    @AfterAll
    public void tearDown() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void createOrder() {
        Order order = new Order();

        order.setProducts(List.of(product1, product2));

        Order created = assertDoesNotThrow(() -> orderService.createOrder(order));

        assertNotNull(created);
        assertNotNull(created.getCode());
        assertEquals(OrderStatus.PLACED, created.getStatus());

        orderCode = created.getCode();
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void createOrderNoProducts() {
        Order order = new Order();

        assertThrows(BadRequestException.class, () -> orderService.createOrder(order));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void getOrderByCode() {
        Order fetched = assertDoesNotThrow(() -> orderService.getOrderByCode(orderCode));
        assertNotNull(fetched);

        assertThrows(NotFoundException.class, () -> orderService.getOrderByCode("somethingWrong"));
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void updateOrder() {
        Order order = new Order();
        order.setProducts(List.of(product1));

        Order updated = assertDoesNotThrow(() -> orderService.updateOrder(orderCode, order));

        assertEquals(order.getProducts().size(), updated.getProducts().size());
        assertEquals(1, order.getProducts().size());
        assertEquals(product1.getName(), order.getProducts().getFirst().getName());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void updateOrderInProgress() {
        Order order = new Order();
        order.setProducts(List.of(product1));

        order = orderService.createOrder(order);

        order.setStatus(OrderStatus.IN_PROGRESS);

        Order savedInProgress = orderRepository.save(order);

        assertThrows(BadRequestException.class, () -> orderService.updateOrder(savedInProgress.getCode(), savedInProgress));
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    public void incrementOrderStatus() {
        Order fetched;

        fetched = assertDoesNotThrow(() -> orderService.incrementOrderStatus(orderCode));
        assertEquals(OrderStatus.IN_PROGRESS, fetched.getStatus());

        fetched = assertDoesNotThrow(() -> orderService.incrementOrderStatus(orderCode));
        assertEquals(OrderStatus.DELIVERY, fetched.getStatus());

        fetched = assertDoesNotThrow(() -> orderService.incrementOrderStatus(orderCode));
        assertEquals(OrderStatus.COMPLETED, fetched.getStatus());

        assertThrows(BadRequestException.class, () -> orderService.incrementOrderStatus(orderCode));
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    public void getDailyOrders() {
        List<Order> orders = assertDoesNotThrow(() -> orderService.getDailyOrders());

        assertFalse(orders.isEmpty());
        assertEquals(1, orders.stream().filter(order -> order.getCode().equals(orderCode)).count());
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    public void deleteOrder() {
        assertThrows(BadRequestException.class, () -> orderService.deleteOrder(orderCode));

        Order order = new Order();
        order.setProducts(List.of(product1));

        Order created = orderService.createOrder(order);

        assertDoesNotThrow(() -> orderService.deleteOrder(created.getCode()));

        Order fetched = orderService.getOrderByCode(created.getCode());

        assertEquals(OrderStatus.CANCELLED, fetched.getStatus());

        assertDoesNotThrow(() -> orderService.deleteOrder("somethingNotExisting"));
    }

}