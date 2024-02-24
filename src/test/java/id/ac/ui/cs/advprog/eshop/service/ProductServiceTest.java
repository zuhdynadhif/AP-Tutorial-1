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
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productService.create(product);
        assertNotNull(productService.findById(product.getId()));
    }

    @Test
    void editTest(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productService.create(product);
        product.setName("Sampo Cap Bambang");
        product.setQuantity(50);
        productService.update(product.getId(), product);
        assertEquals(50, productService.findById(product.getId()).getQuantity());
    }

    @Test
    void findAllTest(){
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productService.create(product1);
        Product product2 = new Product();
        product2.setName("Sampo Cap Bambang");
        product2.setQuantity(50);
        productService.create(product2);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    void findByIdTest(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productService.create(product);
        assertNotNull(productService.findById(product.getId()));
    }

    @Test
    void deleteTest(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productService.create(product);
        productService.deleteById(product.getId());
        assertNull(productService.findById(product.getId()));
    }
}