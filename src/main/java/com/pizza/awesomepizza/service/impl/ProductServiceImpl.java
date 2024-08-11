package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.exceptions.ConflictException;
import com.pizza.awesomepizza.exceptions.NotFoundException;
import com.pizza.awesomepizza.model.Product;
import com.pizza.awesomepizza.repository.ProductRepository;
import com.pizza.awesomepizza.service.FileItemService;
import com.pizza.awesomepizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileItemService fileItemService;

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ConflictException("Product with name '" + product.getName() + "' already exists.");
        }

        product.setId(null);

        fileItemService.markFile(product.getFileId(), false);

        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        if (productRepository.existsByNameAndIdNot(product.getName(), id)) {
            throw new ConflictException("Product with name '" + product.getName() + "' already exists.");
        }

        Product fetched = getProduct(id);

        fetched.setName(product.getName());
        fetched.setIngredients(product.getIngredients());
        fetched.setPrice(product.getPrice());
        fetched.setCategory(product.getCategory());

        if (!fetched.getFileId().equals(product.getFileId())) {
            fileItemService.markFile(product.getFileId(), false);
            fileItemService.markFile(fetched.getFileId(), true);

            fetched.setFileId(product.getFileId());
        }

        return productRepository.save(fetched);
    }

    @Override
    public void deleteProduct(String id) {
        try {
            Product product = getProduct(id);

            fileItemService.markFile(product.getFileId(), true);

            productRepository.delete(product);
        } catch (NotFoundException e) {
            log.warn("Product with id '{}' not found.", id);
        }
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
