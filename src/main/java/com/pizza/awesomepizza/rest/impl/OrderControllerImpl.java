package com.pizza.awesomepizza.rest.impl;

import com.pizza.awesomepizza.dto.OrderDTO;
import com.pizza.awesomepizza.mapper.OrderMapper;
import com.pizza.awesomepizza.rest.OrderController;
import com.pizza.awesomepizza.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.createOrder(orderMapper.toModel(orderDTO))));
    }

    @Override
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderCode") String orderCode) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.getOrderByCode(orderCode)));
    }

    @Override
    public ResponseEntity<OrderDTO> updateOrder(String orderCode, OrderDTO orderDTO) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.updateOrder(orderCode, orderMapper.toModel(orderDTO))));
    }

    @Override
    public ResponseEntity<OrderDTO> incrementOrderStatus(String orderCode) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.incrementOrderStatus(orderCode)));
    }

    @Override
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderCode") String orderCode) {
        orderService.deleteOrder(orderCode);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getDailyOrders() {
        return ResponseEntity.ok(orderMapper.toDto(orderService.getDailyOrders()));
    }

}
