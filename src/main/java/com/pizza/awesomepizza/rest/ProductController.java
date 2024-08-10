package com.pizza.awesomepizza.rest;

import com.pizza.awesomepizza.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/products")
public interface ProductController {

    @PostMapping
    ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO product);

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO product);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<ProductDTO>> getProducts();

}
