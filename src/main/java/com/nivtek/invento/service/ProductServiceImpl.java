package com.nivtek.invento.service;

import com.nivtek.invento.dao.ProductRepository;
import com.nivtek.invento.dao.SupplierRepository;
import com.nivtek.invento.exceptions.ResourceNotFoundException;
import com.nivtek.invento.model.Product;
import com.nivtek.invento.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int productid) {
        return productRepository.findById(productid);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(int supplierId, Product product) {
        Set<Product> products = new HashSet<>();
        Supplier supplier = new Supplier();

        Optional<Supplier> supplierById =  supplierRepository.findById(supplierId);
        if(!supplierById.isPresent()) {
            throw new ResourceNotFoundException("Supplier with Id " + supplierId + " Doesn't Exist! ");
        }

        Supplier supplierinfo = supplierById.get();
        // tie this supplierinfo to the product
        product.setSupplier(supplierinfo);

        Product savedProduct = productRepository.save(product);

        //tie the product to supplier
        products.add(savedProduct);
        supplier.setProducts(products);

        return savedProduct;
    }
}
