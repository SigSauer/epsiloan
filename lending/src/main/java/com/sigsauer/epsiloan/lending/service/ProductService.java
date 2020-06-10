package com.sigsauer.epsiloan.lending.service;

import com.sigsauer.epsiloan.lending.entity.Product;
import com.sigsauer.epsiloan.lending.entity.ProductProperties;
import com.sigsauer.epsiloan.lending.exceptions.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductService {

    Product add(Product product) throws ProductUneditableException;

    List<Product> getAll();

    Product get(@NotNull Long id) throws ProductNotFoundException;

    Product get(@NotNull String title) throws ProductNotFoundException;

    Product update(@NotNull Long id, Product updated) throws ProductUneditableException, ProductNotFoundException;

    boolean delete(@NotNull Long id) throws ProductUneditableException, ProductNotFoundException;

    List<Product> matches(Product product);

    Product duplicate(@NotNull Long id) throws ProductNotFoundException;

    Product configure(@NotNull Long id, ProductProperties properties);







}
