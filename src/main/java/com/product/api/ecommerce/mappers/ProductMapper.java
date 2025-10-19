package com.product.api.ecommerce.mappers;

import com.product.api.ecommerce.dto.ProductDto;
import com.product.api.ecommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
