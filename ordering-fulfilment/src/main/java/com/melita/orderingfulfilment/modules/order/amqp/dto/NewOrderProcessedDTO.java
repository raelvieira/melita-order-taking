package com.melita.orderingfulfilment.modules.order.amqp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.melita.orderingfulfilment.modules.order.model.Order;

import java.util.List;

@JsonDeserialize
public record NewOrderProcessedDTO(CustomerDetailsDTO customer, List<ProductsDTO> products) {
    public Order toOrder() {
        Order entity = new Order();

        entity.setCustomerName(customer().userName());
        entity.setCustomerEmail(customer().email());
        entity.setCustomerDocument(customer().document());
        entity.setInstallationAddress(customer().installationAddress());
        entity.setInstallationDate(customer().installationDate());
        entity.setStartTime(customer().startTime());
        entity.setEndTime(customer().endTime());

        StringBuilder productsListed = new StringBuilder();

        products().forEach(product -> productsListed
            .append("[")
            .append(product.product())
            .append(" - ")
            .append(product.packageProduct())
            .append("], ")
        );

        entity.setProducts(productsListed.toString());

        return entity;
    }
}