package com.product.api.ecommerce.controllers;

import com.product.api.ecommerce.dto.ProductDto;
import com.product.api.ecommerce.dto.ProductResponse;
import com.product.api.ecommerce.exceptions.ProductNotFoundException;
import com.product.api.ecommerce.mappers.ProductMapper;
import com.product.api.ecommerce.models.Product;
import com.product.api.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductDetails(@PathVariable(name="id") Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("The product you are looking is not found!"));

        return ResponseEntity.ok(productMapper.toDto(product));
    }


    @GetMapping()
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestHeader(name = "x-auth-token", required = false) String authToken,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(name = "categoryId", defaultValue = "", required = false) Byte categoryId
        ){



        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products;

        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else {
            products = productRepository.findAllWithCategory(pageable);
        }

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
