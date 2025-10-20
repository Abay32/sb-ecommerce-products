package com.product.api.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String product_name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;

}
