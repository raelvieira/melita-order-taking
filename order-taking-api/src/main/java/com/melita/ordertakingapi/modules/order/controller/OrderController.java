package com.melita.ordertakingapi.modules.order.controller;

import com.melita.ordertakingapi.modules.order.controller.dto.request.NewOrderRequest;
import com.melita.ordertakingapi.modules.order.service.CreateOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @PostMapping
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid NewOrderRequest request) {
        this.createOrderService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
