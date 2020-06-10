package com.sigsauer.epsiloan.lending.service.implementation;

import com.sigsauer.epsiloan.lending.entity.Product;
import com.sigsauer.epsiloan.lending.entity.ProductProperties;
import com.sigsauer.epsiloan.lending.exceptions.ProductNotFoundException;
import com.sigsauer.epsiloan.lending.exceptions.ProductUneditableException;
import com.sigsauer.epsiloan.lending.repository.ProductRepository;
import com.sigsauer.epsiloan.lending.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product add(Product product) throws ProductUneditableException {
        if(product == null) product = new Product();
        if(product.getId() != null && repository.existsById(product.getId())) {
            //usage checking
            log.warn("Instance with this id already exist");
            throw new ProductUneditableException(product.getId(), "Product already exists");
        }

        Product savedProduct = repository.save(product);
        log.info("Saved instance: "+savedProduct);
        return savedProduct;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product get(@NotNull Long id) throws ProductNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product get(@NotNull String title) throws ProductNotFoundException {
        return repository.findByTitle(title)
                .orElseThrow(() -> new ProductNotFoundException(title));
    }

    @Override
    public Product update(@NotNull Long id, Product updated)
            throws ProductUneditableException, ProductNotFoundException {
        return repository.findById(id)
                .map(p -> {
                    if(updated == null) return p;

                    //usage checking
                    if(!updated.getTitle().isEmpty()) p.setTitle(updated.getTitle());
                    if(!updated.getDescription().isEmpty()) p.setDescription(updated.getDescription());
                    if(updated.getProperties() != null) p.setProperties(updated.getProperties());
                    p.setId(id);


                    p = repository.save(p);
                    log.info("Updated instance "+p);
                    return p;
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public boolean delete(@NotNull Long id) throws ProductUneditableException, ProductNotFoundException {
        //usage checking
        repository.delete(get(id));
        return !repository.existsById(id);
    }

    @Override
    public List<Product> matches(Product product) {
        return null;
    }


    public Product duplicate(@NotNull Product product) throws ProductNotFoundException {
        return duplicate(product.getId());
    }

    @Override
    public Product duplicate(@NotNull Long id) throws ProductNotFoundException {
        return duplicate(get(id));
    }

    public Product configure(@NotNull Long id, ProductProperties properties) {
        Product product = get(id);

        product.setProperties(properties);
        return update(id, product);
    }

}

