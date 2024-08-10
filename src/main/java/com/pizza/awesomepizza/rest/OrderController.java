package com.pizza.awesomepizza.rest;

import com.pizza.awesomepizza.dto.OrderDTO;
import com.pizza.awesomepizza.dto.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/orders")
public interface OrderController {

    /**
     * Creates a new order based on the provided OrderDTO.
     *
     * @param orderDTO the OrderDTO object containing the details of the order to be created
     * @return a ResponseEntity containing the created OrderDTO
     */
    @PostMapping
    ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO);

    /**
     * Retrieves an order based on the provided order code.
     *
     * @param orderCode the code of the order to retrieve
     * @return a ResponseEntity containing the OrderDTO of the retrieved order
     */
    @GetMapping("/{orderCode}")
    ResponseEntity<OrderDetailDTO> getOrder(@PathVariable("orderCode") String orderCode);

    /**
     * Updates an existing order with the provided details,
     * ensuring the order is in {@link com.pizza.awesomepizza.enumeration.OrderStatus#PLACED} status.
     *
     * @param orderDTO the OrderDTO object containing the updated details
     * @return the updated OrderDTO object
     */
    @PutMapping("/{orderCode}")
    ResponseEntity<OrderDTO> updateOrder(@PathVariable("orderCode") String orderCode,
                                         @Valid @RequestBody OrderDTO orderDTO);

    /**
     * Updates an existing order with the provided details,
     * ensuring the order is in {@link com.pizza.awesomepizza.enumeration.OrderStatus#PLACED} status.
     *
     * @return the updated OrderDTO object
     */
    @PutMapping("/{orderCode}/status")
    ResponseEntity<OrderDTO> incrementOrderStatus(@PathVariable("orderCode") String orderCode);

    /**
     * Deletes an order based on the provided order code.
     *
     * @param orderCode the code of the order to delete
     * @return a ResponseEntity with a status indicating the result of the operation
     */
    @DeleteMapping("/{orderCode}")
    ResponseEntity<Void> deleteOrder(@PathVariable("orderCode") String orderCode);

    /**
     * Retrieves a list of orders placed on the current day.
     *
     * @return a ResponseEntity containing a list of OrderDTO for today's orders
     */
    @GetMapping
    ResponseEntity<List<OrderDetailDTO>> getDailyOrders();

}
