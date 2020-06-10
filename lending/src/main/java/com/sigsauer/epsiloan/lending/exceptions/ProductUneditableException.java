package com.sigsauer.epsiloan.lending.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ProductUneditableException extends RuntimeException{

    public ProductUneditableException() {
        super("Probably, forbidden action!");
    }

    public ProductUneditableException(Long id) {
        super("Action cannot be performed: Product with ID = " + id + " is being used");
    }

    public ProductUneditableException(String reason) {
        super("Action cannot be performed: "+reason);
    }

    public ProductUneditableException(Long id, String reason) {
        super("Action cannot be performed: Product with ID = " + id + " is uneditable. "+reason);
    }


}
