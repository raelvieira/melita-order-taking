package com.melita.ordertakingapi.modules.product.service.impl;

import com.melita.ordertakingapi.configuration.exception.NotFoundException;
import com.melita.ordertakingapi.modules.product.model.Product;
import com.melita.ordertakingapi.modules.product.repository.ProductRepository;
import com.melita.ordertakingapi.modules.product.service.ProductSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchImpl implements ProductSearch {
    private final ProductRepository productRepository;

    public ProductSearchImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByIdList(List<Long> idList) {
        List<Product> productList = this.productRepository.findByIdList(idList);

        if (productList.isEmpty()) {
            throw new NotFoundException("No product found for the informed IDs");
        }

        return productList;
    }
}
