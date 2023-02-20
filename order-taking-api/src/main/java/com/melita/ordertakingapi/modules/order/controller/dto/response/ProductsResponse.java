package com.melita.ordertakingapi.modules.order.controller.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.melita.ordertakingapi.modules.product.model.PackageProduct;
import com.melita.ordertakingapi.modules.product.model.Product;

@JsonSerialize
public record ProductsResponse(
    String product,
    String packageProduct
) {
    public static ProductsResponse create(PackageProduct packageProduct) {
        Product product = packageProduct.getProduct();

        return new ProductsResponse(
            product.getName(),
            packageProduct.getName()
        );
    }
}
