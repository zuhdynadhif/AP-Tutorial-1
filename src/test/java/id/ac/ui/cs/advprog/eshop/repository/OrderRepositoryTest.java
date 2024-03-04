package id.ac.ui.cs.advprog.eshop.repository;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    OrderRepository orderRepository;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("1", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("2", products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("3", products, 1708580000L, "Safira Sudrajat");
        orders.add(order3);
    }

    @Test
    void testSaveCreate() {
        Order order = orders.get(1);
        Order result = orderRepository.save(order);

        Order findResult = orderRepository.findById(order.getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Order order = orders.get(1);
        orderRepository.save(order);

        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(), OrderStatus.SUCCESS.getValue());
        Order result = orderRepository.save(newOrder);

        Order findResult = orderRepository.findById(orders.get(1).getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus());
    }
    @Test
    void testFindByIdFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }
        Order order = orders.get(1);
        Order findResult = orderRepository.findById(order.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus());
    }
    @Test
    void testFindByIdNotFound() {
        for (Order order : orders){
            orderRepository.save(order);
        }
        Order findResult = orderRepository.findById("ngasal");
        assertNull(findResult);
    }
    @Test
    void testFindAllByAuthorIfAuthorCorrect(){
        for (Order order : orders){
            orderRepository.save(order);
        }

        List<Order> orderList = orderRepository.findAllByAuthor(
                orders.get(1).getAuthor());
        assertEquals(2, orderList.size());
    }
    @Test
    void testFindAllByAuthotIfAllLowercase() {
        for(Order order : orders){
            orderRepository.save(order);
        }

        List<Order> orderList = orderRepository.findAllByAuthor("ngasal");
        assertTrue(orderList.isEmpty());
    }
}