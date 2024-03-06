package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-0128-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        paymentService = new PaymentServiceImpl();
        payments = new ArrayList<>();
        Payment voucherPayment = new Payment(order1.getId(), PaymentMethod.VOUCHER_CODE.getValue(),
                Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"));
        payments.add(voucherPayment);
        Payment bankPayment = new Payment(order1.getId(), PaymentMethod.BANK_TRANSFER.getValue(),
                Map.of("bankName", "BRI", "referenceCode", "1234567890"));
        payments.add(bankPayment);
    }

    // happy : add new payment
    @Test
    void testAddNewPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        Order order = orders.getFirst();

        Payment result = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(),
                payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    // happy : add already exist payment
    @Test
    void testAddAlreadyExistPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Order order = orders.getFirst();

        Payment result = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(),
                payment.getPaymentData());

        verify(paymentRepository, times(0)).save(any(Payment.class));
        assertNull(result);
    }

    // happy : set status with valid status
    @Test
    void testSetStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                payment.getPaymentData(), PaymentStatus.SUCCESS.getValue());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    // unhappy : set status with invalid status
    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "NGASAL"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    // unhappy : set status with null Payment
    @Test
    void testSetStatusNullPayment() {
        assertThrows(NullPointerException.class,
                () -> paymentService.setStatus(null, PaymentStatus.SUCCESS.getValue()));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    // happy : get payment with valid id
    @Test
    void testGetPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData().keySet(), result.getPaymentData().keySet());
        assertEquals(payment.getPaymentData().get("bankName"),
                result.getPaymentData().get("bankName"));
        assertEquals(payment.getPaymentData().get("referenceCode"),
                result.getPaymentData().get("referenceCode"));
        assertEquals(payment.getStatus(), result.getStatus());
    }

    // unhappy : get payment with invalid id
    @Test
    void testGetPaymentInvalidId() {
        assertThrows(NullPointerException.class,
                () -> paymentService.getPayment("NGASAL"));
        verify(paymentRepository, times(0)).findById(anyString());
    }

    // happy : get all payments
    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(payments.size(), result.size());
        verify(paymentRepository, times(1)).findAll();
    }

    // unhappy : get all payments with empty payment
    @Test
    void testGetAllPaymentsEmpty() {
        doReturn(new ArrayList<>()).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        verify(paymentRepository, times(1)).findAll();
        assertEquals(0, result.size());
    }
}
