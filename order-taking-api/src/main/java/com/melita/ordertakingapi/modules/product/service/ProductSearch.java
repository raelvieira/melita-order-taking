package com.melita.ordertakingapi.modules.product.service;

import com.melita.ordertakingapi.modules.product.model.Product;

import java.util.List;

public interface ProductSearch {
    List<Product> findByIdList(List<Long> idList);
}
