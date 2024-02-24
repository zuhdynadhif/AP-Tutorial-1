package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements EntityRepository<Product> {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setId(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        return product;
    }

    public Product update(String productId, Product updatedProduct) {
        for (Product p : productData){
            if (p.getId().equals(productId)){
                p.setName(updatedProduct.getName());
                p.setQuantity(updatedProduct.getQuantity());
                return p;
            }
        }
        return null;
    }


    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        return productData.stream().filter(product -> product.getId().equals(productId)).findFirst().orElse(null);
    }


    public void delete(String productId) {
        productData.removeIf(product -> product.getId().equals(productId));
    }
}