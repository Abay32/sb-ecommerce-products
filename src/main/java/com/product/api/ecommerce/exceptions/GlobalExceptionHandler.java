package com.product.api.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorObject> handleProductNotFoundException(ProductNotFoundException e){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError(e.getMessage());
        errorObject.setStatus(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleAlreadyExistsException(AlreadyExistsException e){
        ErrorObject err = new ErrorObject();
        err.setError(e.getMessage());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setTimestamp(new Date());

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}
