package com.nivtek.invento.api;

import com.nivtek.invento.dao.ProductRepository;
import com.nivtek.invento.exceptions.ResourceNotFoundException;
import com.nivtek.invento.model.Product;
import com.nivtek.invento.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierController supplierController;

    @Autowired
    private ProductRepository productRepository;

    /**
     * This endpoint is being called when angular-client submits the login form
     *
     * @param user
     * @return Principal Object with all auth information
     */
    @GetMapping("/basicauth")
    public Principal basicAuth(Principal user) {
        return user;
    }

    /**
     * @return List of Product Objects. If no Products are present then it returns empty list.
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    /**
     * This endpoint is meant to return individual Product object for given id.
     *
     * @param productid
     * @return Product Object.
     */
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(value = "id") int productid) {
        return productService.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Product Not found with id : " + productid));

    }

    /**
     * This method will delete the product object for requested id.
     *
     * @param productId
     * @return Map<String, Boolean>
     */
    @DeleteMapping("/products/{id}")
    @CrossOrigin("http://localhost:4200")
    public Map<String, Boolean> deleteProducts(@PathVariable(value = "id") int productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Not Found Product: " + productId)
        );

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("removed", Boolean.TRUE);

        return response;
    }


    /**
     * This method returns all the products for particular supplier.
     *
     * @param id
     * @return List<Product>
     */
    @GetMapping("/suppliers/{id}/products")
    public List<Product> getAllProductsBySupplierId(@PathVariable(value = "id") int id) {
        return productRepository.findBySupplierId(id);
    }

    /**
     * This method will create a Product object for particular supplier.
     *
     * @param supplierId
     * @param product
     * @return Product Object
     */
    @PostMapping("/suppliers/{id}/products")
    public Product createProduct(@PathVariable(value = "id") int supplierId, @RequestBody Product product) {
        return productService.createProduct(supplierId, product);
    }

}
