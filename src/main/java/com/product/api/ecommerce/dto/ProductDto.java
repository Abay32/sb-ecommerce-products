package com.product.api.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String product_name;
    private String description;
    private BigDecimal price;

    private Byte categoryId;
}
