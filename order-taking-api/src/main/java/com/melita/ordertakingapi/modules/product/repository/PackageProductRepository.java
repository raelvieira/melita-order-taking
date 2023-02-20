package com.melita.ordertakingapi.modules.product.repository;

import com.melita.ordertakingapi.modules.product.model.PackageProduct;
import com.melita.ordertakingapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageProductRepository extends JpaRepository<PackageProduct, Long> {
    @Query("FROM PackageProduct pp WHERE pp.id IN :packageIdList AND pp.product.id IN (:productIdList)")
    List<PackageProduct> findByIdList(@Param("packageIdList") List<Long> packageProductIdList,
                                      @Param("productIdList") List<Long> productIdList);
}
