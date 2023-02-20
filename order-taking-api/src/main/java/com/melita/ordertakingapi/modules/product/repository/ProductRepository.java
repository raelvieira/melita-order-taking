package com.melita.ordertakingapi.modules.product.repository;

import com.melita.ordertakingapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product p WHERE p.id IN :productIdList")
    List<Product> findByIdList(@Param("productIdList") List<Long> productIdList);
}
