package com.sigsauer.epsiloan.lending.exceptions;

import com.sigsauer.epsiloan.lending.enums.ProductProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductPropertyException extends RuntimeException {

    public InvalidProductPropertyException() {
        super("Configuration error: Missing required parameters");
    }

    public InvalidProductPropertyException(String property) {
        super("Can not configure "+ property +": Property has not found");
    }

    public InvalidProductPropertyException(ProductProperty property) {
        super("Can not configure "+ property +": Setup method has not found or value processing error");
    }

    public InvalidProductPropertyException(ProductProperty property, String description) {
        super("Can not configure "+ property +": "+description);
    }

    public InvalidProductPropertyException(ProductProperty... property) {
        super("Following parameters were not configured: "+ Arrays.toString(property));
    }

    public InvalidProductPropertyException(ProductProperty property, String[] keys) {
        super("Can not configure "+ property + (keys.length == 1 ? ": Invalid value of parameter: "+ keys[0]+""
                :": Values of following parameters may cause uncertain of unexpected configuration result: "
                + Arrays.toString(keys)));
    }

    public InvalidProductPropertyException(ProductProperty property, String key, Object value) {
        super("Can not configure "+ property +": Parameter "+ key + " can not be set with value: "+ value);
    }

    @Deprecated
    public InvalidProductPropertyException(Object... o) {
        super("Unexpected result. Invalid values: "+ Arrays.toString(o));
    }






}
