package com.nivtek.invento.service;

import com.nivtek.invento.model.Product;

import java.util.List;
import java.util.Optional;

/*
 * @project Invento
 * @author AsimSubedi created on 5/9/2020
 */
public interface ProductService {

    /**
     * This method will return all the Products along with their Supplier as a List.
     * If no Products are present; This will return empty list.
     *
     * @return List<Product>
     */
    List<Product> findAll();

    /**
     * @param productid
     * @return Optional of Product
     */
    Optional<Product> findById(int productid);

    /**
     * @param product
     * @return Product Object that is saved
     */
    Product saveProduct(Product product);

    Product createProduct(int supplierId, Product product);
}
