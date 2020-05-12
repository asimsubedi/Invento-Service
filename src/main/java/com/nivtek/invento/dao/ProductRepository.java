package com.nivtek.invento.dao;

import com.nivtek.invento.model.Product;
import com.nivtek.invento.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findBySupplierId(int supplierId);

    Optional<Product> findByIdAndSupplierId(int id, int supplierId);

    @Query("select supplier.id from Product where id= :id")
    int findSupplierByProductId(@Param("id") int productId);
}
