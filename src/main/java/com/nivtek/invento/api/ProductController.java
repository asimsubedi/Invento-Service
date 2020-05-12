package com.nivtek.invento.api;

import com.nivtek.invento.dao.ProductRepository;
import com.nivtek.invento.exceptions.ResourceNotFoundException;
import com.nivtek.invento.model.Product;
import com.nivtek.invento.model.Supplier;
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
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierController supplierController;

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
        List<Object[]> sqlResult = productRepository.findSupplierByProductId(productid);
        System.out.println(sqlResult.size() +  " has sized ");
        Integer thisProductSupplierId = (Integer) sqlResult.get(0)[0];
        System.out.println(thisProductSupplierId + " here");
        Supplier thisSupplier = supplierController.getSupplier(thisProductSupplierId);
        Product responseProduct = productService.findById(productid)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Product Not found with id : " + productid));

        responseProduct.setSupplier(thisSupplier);

        System.out.println(responseProduct);
        return responseProduct;
    }

    @PostMapping("/products")
    public Product mysql(@RequestBody Product product) {
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
    public List<Product> getAllProductsBySupplierId(@PathVariable(value = "id") int id) {
        return productRepository.findBySupplierId(id);
    }

    @PostMapping("/suppliers/{id}/products")
    public Product createProduct(@PathVariable(value = "id") int supplierId, @RequestBody Product product) {
        return productService.createProduct(supplierId, product);
    }

}
