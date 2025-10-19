package com.product.api.ecommerce.exceptions;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;
    public ProductNotFoundException(String error){
        super(error);
    }
}
