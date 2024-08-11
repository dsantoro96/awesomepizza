package com.pizza.awesomepizza.service.impl;

import com.pizza.awesomepizza.AwesomePizzaApplication;
import com.pizza.awesomepizza.enumeration.ProductCategory;
import com.pizza.awesomepizza.exceptions.ConflictException;
import com.pizza.awesomepizza.exceptions.NotFoundException;
import com.pizza.awesomepizza.model.FileItem;
import com.pizza.awesomepizza.model.Product;
import com.pizza.awesomepizza.repository.ProductRepository;
import com.pizza.awesomepizza.service.FileItemService;
import com.pizza.awesomepizza.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {AwesomePizzaApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileItemService fileItemService;

    @Autowired
    private ProductRepository productRepository;

    private String id;
    private MockMultipartFile multipartFile;

    @BeforeAll
    public void setUp() {
        multipartFile = new MockMultipartFile(
                "file",
                "test_image.png",
                MediaType.IMAGE_PNG_VALUE,
                "Mock".getBytes()
        );
    }

    @AfterAll
    public void tearDown() {
        productRepository.deleteAll();
        fileItemService.deleteTemporaryFiles();
    }

    @Test
    @Order(1)
    public void createProduct() {
        FileItem fileItem = fileItemService.uploadFile(multipartFile);

        Product product = new Product();

        product.setName("Pizza Margherita");
        product.setCategory(ProductCategory.PIZZAS);
        product.setIngredients(List.of(
                "Pizza Dough",
                "Tomato Sauce",
                "Mozzarella",
                "Basil",
                "Olive Oil"
        ));
        product.setPrice(4.5);
        product.setFileId(fileItem.getId());

        Product created = assertDoesNotThrow(() -> productService.createProduct(product));

        id = created.getId();

    }

    @Test
    @Order(2)
    public void createProductAlreadyExists() {
        Product product = new Product();

        product.setName("Pizza Margherita");
        product.setCategory(ProductCategory.PIZZAS);
        product.setIngredients(List.of(
                "Pizza Dough",
                "Tomato Sauce",
                "Mozzarella",
                "Basil",
                "Olive Oil"
        ));
        product.setPrice(4.5);

        assertThrows(ConflictException.class, () -> productService.createProduct(product));
    }

    @Test
    @Order(3)
    public void getProduct() {
        assertDoesNotThrow(() -> productService.getProduct(id));

        assertThrows(NotFoundException.class, () -> productService.getProduct("somethingWrong"));
    }

    @Test
    @Order(4)
    public void updateProduct() {
        FileItem fileItem = fileItemService.uploadFile(multipartFile);

        Product product = new Product();

        product.setName("Pizza Marinara");
        product.setCategory(ProductCategory.PIZZAS);
        product.setIngredients(List.of(
                "Pizza Dough",
                "Tomato Sauce",
                "Garlic",
                "Oregano",
                "Olive Oil"
        ));
        product.setPrice(3.5);
        product.setFileId(fileItem.getId());

        assertDoesNotThrow(() -> productService.updateProduct(id, product));
        assertThrows(ConflictException.class, () -> productService.updateProduct("somethingWrong", product));
    }

    @Test
    @Order(5)
    public void getProducts() {
        List<Product> products = assertDoesNotThrow(() -> productService.getProducts());

        assertFalse(products.isEmpty());
        assertEquals("Pizza Marinara", products.getFirst().getName());
    }

    @Test
    @Order(6)
    public void deleteProduct() {
        assertDoesNotThrow(() -> productService.deleteProduct(id));
        assertDoesNotThrow(() -> productService.deleteProduct("somethingWrong"));
    }

}