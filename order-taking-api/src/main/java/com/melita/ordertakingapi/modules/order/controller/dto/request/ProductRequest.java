package com.melita.ordertakingapi.modules.order.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
    @NotNull @Positive
    private Long productId;
    @NotNull @Positive
    private Long packageId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageI) {
        this.packageId = packageI;
    }
}
