package com.melita.ordertakingapi.modules.order.service;

import com.melita.ordertakingapi.modules.order.controller.dto.request.NewOrderRequest;

public interface CreateOrderService {
    void execute(NewOrderRequest request);
}
