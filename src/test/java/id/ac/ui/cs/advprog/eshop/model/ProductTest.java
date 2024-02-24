package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        this.product.setId("kdasl3432-234hlk2-2kljklrj-lkwje-lksefs");
        this.product.setName("Sampo Cap Bambang");
        this.product.setQuantity(100);
    }

    @Test
    void testGetId() {
        assertEquals("kdasl3432-234hlk2-2kljklrj-lkwje-lksefs", this.product.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Sampo Cap Bambang", this.product.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(100, this.product.getQuantity());
    }
}