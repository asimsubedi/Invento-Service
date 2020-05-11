package com.nivtek.invento.dao;

import com.nivtek.invento.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findBySupplierId(int supplierId, Pageable pageable);

    Optional<Product> findByIdAndSupplierId(int id, int supplierId);
}
