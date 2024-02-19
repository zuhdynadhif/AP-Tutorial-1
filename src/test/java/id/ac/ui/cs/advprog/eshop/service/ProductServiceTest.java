package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void createTest(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);
        assertNotNull(productService.findById(product.getProductId()));
    }

    @Test
    void editTest(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(50);
        productService.edit(product);
        assertEquals(50, productService.findById(product.getProductId()).getProductQuantity());
    }

    @Test
    void findAllTest(){
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);
        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(50);
        productService.create(product2);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    void findByIdTest(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);
        assertNotNull(productService.findById(product.getProductId()));
    }

    @Test
    void deleteTest(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);
        productService.delete(product.getProductId());
        assertNull(productService.findById(product.getProductId()));
    }
}