package com.sigsauer.epsiloan.lending.controller;

import com.sigsauer.epsiloan.lending.entity.Product;
import com.sigsauer.epsiloan.lending.entity.ProductProperties;
import com.sigsauer.epsiloan.lending.service.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController is controller class that maps Product urls
 *
 * @see Product
 * @see ProductProperties
 * @see com.sigsauer.epsiloan.lending.service.ProductService
 *
 * @author  SigSauer
 * @version 1.0
 */
@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.add(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("{id}/properties")
    public Product update(@PathVariable Long id, @RequestBody ProductProperties properties) {
        return service.configure(id, properties);
    }
}

