package com.melita.ordertakingapi.modules.product.service;

import com.melita.ordertakingapi.modules.product.model.PackageProduct;

import java.util.List;

public interface PackageProductSearch {
    List<PackageProduct> findByIdListAndProduct(List<Long> idList, List<Long> productId);
}
