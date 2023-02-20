package com.melita.ordertakingapi.modules.order.service.impl;

import com.melita.ordertakingapi.modules.order.controller.dto.request.CustomerDetailsRequest;
import com.melita.ordertakingapi.modules.order.controller.dto.request.NewOrderRequest;
import com.melita.ordertakingapi.modules.order.controller.dto.request.ProductRequest;
import com.melita.ordertakingapi.modules.order.controller.dto.response.NewOrderProcessed;
import com.melita.ordertakingapi.modules.order.service.CreateOrderService;
import com.melita.ordertakingapi.modules.product.model.PackageProduct;
import com.melita.ordertakingapi.modules.product.service.PackageProductSearch;
import jakarta.ws.rs.BadRequestException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    private final PackageProductSearch packageProductSearch;

    private final RabbitTemplate rabbitTemplate;

    public CreateOrderServiceImpl(PackageProductSearch packageProductSearch, RabbitTemplate rabbitTemplate) {
        this.packageProductSearch = packageProductSearch;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void execute(NewOrderRequest request) {
        CustomerDetailsRequest customer = request.getCustomer();
        List<ProductRequest> products = request.getProducts();

        if (customer.getStartTime().isAfter(customer.getEndTime())) {
            throw new BadRequestException("End time cannot be earlier than start time");
        }

        List<Long> productIdList = products.stream()
            .map(ProductRequest::getProductId)
            .toList();

        List<Long> packageProductIdList = products.stream()
            .map(ProductRequest::getPackageId)
            .toList();

        List<PackageProduct> packageProducts = this.packageProductSearch
            .findByIdListAndProduct(packageProductIdList, productIdList);

        NewOrderProcessed newOrderProcessed = NewOrderProcessed.create(customer, packageProducts);
        this.rabbitTemplate.convertAndSend("new.order", newOrderProcessed);
    }
}
