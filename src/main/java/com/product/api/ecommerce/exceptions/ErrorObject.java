package com.product.api.ecommerce.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer status;
    private String error;
    private Date timestamp;
}
