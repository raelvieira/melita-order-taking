package com.melita.ordertakingapi.modules.order.controller.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.melita.ordertakingapi.modules.order.controller.dto.request.CustomerDetailsRequest;
import com.melita.ordertakingapi.modules.product.model.PackageProduct;

import java.util.List;

@JsonSerialize
public record NewOrderProcessed(CustomerDetailsRequest customer, List<ProductsResponse> products) {
    public static NewOrderProcessed create(CustomerDetailsRequest customer, List<PackageProduct> packageProductList) {
        List<ProductsResponse> productsResponse = packageProductList.stream()
            .map(ProductsResponse::create)
            .toList();

        return new NewOrderProcessed(
            customer,
            productsResponse
        );
    }
}
