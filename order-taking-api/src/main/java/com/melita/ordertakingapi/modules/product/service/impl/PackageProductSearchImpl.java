package com.melita.ordertakingapi.modules.product.service.impl;

import com.melita.ordertakingapi.configuration.exception.NotFoundException;
import com.melita.ordertakingapi.modules.product.model.PackageProduct;
import com.melita.ordertakingapi.modules.product.repository.PackageProductRepository;
import com.melita.ordertakingapi.modules.product.service.PackageProductSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageProductSearchImpl implements PackageProductSearch {
    private final PackageProductRepository packageProductRepository;

    public PackageProductSearchImpl(PackageProductRepository packageProductRepository) {
        this.packageProductRepository = packageProductRepository;
    }

    @Override
    public List<PackageProduct> findByIdListAndProduct(List<Long> idList, List<Long> productId) {
        List<PackageProduct> packageProducts = this.packageProductRepository.findByIdList(idList, productId);

        if (packageProducts.isEmpty()) {
            throw new NotFoundException("No package found for the informed IDs");
        }

        return packageProducts;
    }
}
