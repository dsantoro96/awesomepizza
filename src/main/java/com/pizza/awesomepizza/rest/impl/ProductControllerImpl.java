package com.pizza.awesomepizza.rest.impl;

import com.pizza.awesomepizza.dto.ProductDTO;
import com.pizza.awesomepizza.mapper.ProductMapper;
import com.pizza.awesomepizza.rest.ProductController;
import com.pizza.awesomepizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @Override
    public ResponseEntity<ProductDTO> createProduct(ProductDTO product) {
        return ResponseEntity.ok(productMapper.toDto(productService.createProduct(productMapper.toModel(product))));
    }

    @Override
    public ResponseEntity<ProductDTO> getProduct(String id) {
        return ResponseEntity.ok(productMapper.toDto(productService.getProduct(id)));
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(String id, ProductDTO product) {
        return ResponseEntity.ok(productMapper.toDto(productService.updateProduct(id, productMapper.toModel(product))));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productMapper.toDto(productService.getProducts()));
    }

}
