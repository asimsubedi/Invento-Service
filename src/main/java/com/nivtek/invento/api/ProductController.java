package com.nivtek.invento.api;

import com.nivtek.invento.model.Product;
import com.nivtek.invento.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@RestController
@RequestMapping(name = "api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(name = "products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
}
