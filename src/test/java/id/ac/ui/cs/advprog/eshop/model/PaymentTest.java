package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @BeforeEach
    void setUp() {
    }

    // test constructor without setting status
    @Test
    void testCreatePaymentNoStatus() {
        Payment payment = new Payment("1", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"));
        assertEquals("1", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"), payment.getPaymentData());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    // test constructor with setting status
    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("2", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"), OrderStatus.SUCCESS.getValue());
        assertEquals("2", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"), payment.getPaymentData());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: empty payment data
    @Test
    void testCreatePaymentEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("3", "VOUCHER_CODE", Map.of());
        });
    }

    // happy: no status defined
    @Test
    void testGetStatusNoStatus() {
        Payment payment = new Payment("4", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"));
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    // happy: status defined SUCCESS
    @Test
    void testGetStatusSuccess() {
        Payment payment = new Payment("5", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"), OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: status invalid
    @Test
    void testGetStatusInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("6", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"), "INVALID_STATUS");
        });
    }

    // happy: edit status
    @Test
    void testSetStatus() {
        Payment payment = new Payment("7", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"));
        payment.setStatus(OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: edit status with invalid status
    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = new Payment("8", "VOUCHER_CODE", Map.of("VOUCHER_CODE", "ESHOP1234ABC5678"));
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }
}