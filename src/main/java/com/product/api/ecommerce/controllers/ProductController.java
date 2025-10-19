package com.product.api.ecommerce.controllers;

import com.product.api.ecommerce.dto.ProductDto;
import com.product.api.ecommerce.dto.ProductResponse;
import com.product.api.ecommerce.mappers.ProductMapper;
import com.product.api.ecommerce.models.Product;
import com.product.api.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
        ){

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);

        List<Product> listOfProducts = products.getContent();

        List<ProductDto> content = listOfProducts.stream()
                .map(productMapper::toDto)
                .toList();

        ProductResponse prodRes = new ProductResponse();
        prodRes.setContent(content);
        prodRes.setPageNo(pageNo);
        prodRes.setPageSize(pageSize);
        prodRes.setTotalElements(products.getTotalElements());
        prodRes.setTotalPages(products.getTotalPages());
        prodRes.setLast(products.isLast());

        return new ResponseEntity<>(prodRes, HttpStatus.OK);

    }

}
