package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.components.RandomStringGenerator;
import com.pizza.awesomepizza.enumeration.OrderStatus;
import com.pizza.awesomepizza.exceptions.BadRequestException;
import com.pizza.awesomepizza.exceptions.NotFoundException;
import com.pizza.awesomepizza.model.Order;
import com.pizza.awesomepizza.model.Product;
import com.pizza.awesomepizza.repository.OrderRepository;
import com.pizza.awesomepizza.repository.ProductRepository;
import com.pizza.awesomepizza.service.OrderService;
import com.pizza.awesomepizza.utils.Constants;
import com.pizza.awesomepizza.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RandomStringGenerator randomStringGenerator;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        List<String> productIds = order.getProducts().stream().map(Product::getId).toList();

        if (productIds.isEmpty() || !productRepository.existsAllByIdIn(productIds)) {
            throw new BadRequestException("Some of the products in the order are not available.");
        }

        String orderCode = generateOrderCode();

        order.setId(null);
        order.setCode(orderCode);
        order.setOrderDate(LocalDateTime.now(ZoneOffset.UTC));
        order.setStatus(OrderStatus.PLACED);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByCode(String code) {
        return orderRepository.findByCodeAndOrderDateGreaterThan(code, DateUtils.startOfDay())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Order updateOrder(String orderCode, Order order) {
        Order fetched = getOrderByCode(orderCode);

        if (!fetched.getStatus().equals(OrderStatus.PLACED)) {
            throw new BadRequestException("Cannot modify an order that is not in a placed status.");
        }

        fetched.setProducts(order.getProducts());

        return orderRepository.save(fetched);
    }

    @Override
    public Order incrementOrderStatus(String orderCode) {
        Order fetched = getOrderByCode(orderCode);

        OrderStatus status = fetched.getStatus();

        if (status.equals(OrderStatus.COMPLETED) || status.equals(OrderStatus.CANCELLED)) {
            throw new BadRequestException("Cannot change the status of an order that is in a " + status + " status.");
        }

        OrderStatus newStatus = getNextOrderStatus(status);

        fetched.setStatus(newStatus);

        return orderRepository.save(fetched);
    }

    @Override
    public void deleteOrder(String orderCode) {
        try {
            Order order = getOrderByCode(orderCode);

            if (!order.getStatus().equals(OrderStatus.PLACED)) {
                throw new BadRequestException("Cannot modify an order that is not in a placed status.");
            }

            order.setStatus(OrderStatus.CANCELLED);

            orderRepository.save(order);
        } catch (NotFoundException e) {
            log.warn("Could not find order: {}", orderCode);
        }
    }

    @Override
    public List<Order> getDailyOrders() {
        return orderRepository.findAllByOrderDateGreaterThan(DateUtils.startOfDay());
    }

    /**
     * Generates a unique order code until a non-existing one is found in the database.
     *
     * @return a unique order code as a {@link String}
     */
    private String generateOrderCode() {
        String orderCode = null;
        boolean codeExists = true;

        while (codeExists) {
            orderCode = randomStringGenerator.generateString(Constants.ORDER_CODE_LENGTH);

            codeExists = orderRepository.existsByCodeAndOrderDateGreaterThan(orderCode, DateUtils.startOfDay());
        }

        return orderCode;
    }

    /**
     * Gets the next {@link OrderStatus}.
     *
     * @param status the current {@link OrderStatus}
     * @return the next {@link OrderStatus}
     * @throws ArrayIndexOutOfBoundsException if the current status is the last one
     */
    private OrderStatus getNextOrderStatus(OrderStatus status) {
        return OrderStatus.values()[status.ordinal() + 1];
    }

}
