package com.melita.ordertakingapi.modules.order.controller.dto.request;

import java.util.List;

public class NewOrderRequest {
    private CustomerDetailsRequest customer;
    private List<ProductRequest> products;

    public CustomerDetailsRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDetailsRequest customer) {
        this.customer = customer;
    }

    public List<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(
        List<ProductRequest> products) {
        this.products = products;
    }
}
