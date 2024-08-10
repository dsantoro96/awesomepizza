package com.pizza.awesomepizza.service;

import com.pizza.awesomepizza.dto.OrderDetailDTO;
import com.pizza.awesomepizza.model.Order;

import java.util.List;

public interface OrderService {

    /**
     * Creates a new order based on the provided Order object.
     *
     * @param order the Order object containing the details of the order to be created
     * @return the created Order object
     */
    Order createOrder(Order order);

    /**
     * Retrieves an order based on the provided order code.
     *
     * @param code the code of the order to retrieve
     * @return the Order object of the retrieved order
     */
    OrderDetailDTO getOrderByCode(String code);

    /**
     * Updates an existing order with the provided details,
     * ensuring the order is in {@link com.pizza.awesomepizza.enumeration.OrderStatus#PLACED} status.
     *
     * @param order the Order object containing the updated details
     * @return the updated Order object
     */
    Order updateOrder(String orderCode, Order order);

    Order incrementOrderStatus(String orderCode);

    /**
     * Deletes an order based on the provided order code.
     *
     * @param orderCode the code of the order to delete
     */
    void deleteOrder(String orderCode);

    /**
     * Retrieves a list of orders placed on the current day.
     *
     * @return a list of Order objects for today's orders
     */
    List<OrderDetailDTO> getDailyOrders();

}
