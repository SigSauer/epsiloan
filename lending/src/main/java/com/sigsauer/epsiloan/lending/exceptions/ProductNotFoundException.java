package com.sigsauer.epsiloan.lending.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Not found Product with ID = " + id);
    }

    public ProductNotFoundException(String title) {
        super("Not found Product with TITLE = " + title);
    }
}
