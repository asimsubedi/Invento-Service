package com.nivtek.invento.api;

import com.nivtek.invento.dao.ProductRepository;
import com.nivtek.invento.exceptions.ResourceNotFoundException;
import com.nivtek.invento.model.Product;
import com.nivtek.invento.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    /**
     * @return List of Product Objects. If no Products are present then it returns empty list.
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(value = "id") int productid) {
        return this.productService.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Product Not found with id : " + productid));
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") int productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Sorry!! product of id: " + productId + " Not Found!!!"));
    }

    @GetMapping("/suppliers/{id}/products")
    public Page<Product> getAllProductsBySupplierId(@PathVariable(value = "id") int id, Pageable pageable) {
        return productRepository.findBySupplierId(id, pageable);
    }

    @PostMapping("/suppliers/{id}/products")
    public Product createProduct(@PathVariable(value = "id") int supplierId, @RequestBody Product product) {
        return productService.createProduct(supplierId, product);
    }

}
