package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import enums.PaymentMethod;
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
        Payment payment = new Payment("1", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"));
        assertEquals("1", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"), payment.getPaymentData());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    // test constructor with setting status
    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("2", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"), PaymentStatus.SUCCESS.getValue());
        assertEquals("2", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"), payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: empty payment data
    @Test
    void testCreatePaymentEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("3", PaymentMethod.VOUCHER_CODE.getValue(), Map.of());
        });
    }

    // happy: no status defined
    @Test
    void testGetStatusNoStatus() {
        Payment payment = new Payment("4", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"));
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    // happy: status defined SUCCESS
    @Test
    void testGetStatusSuccess() {
        Payment payment = new Payment("5", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"), PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: status invalid
    @Test
    void testGetStatusInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("6", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"), "INVALID_STATUS");
        });
    }

    // happy: edit status
    @Test
    void testSetStatus() {
        Payment payment = new Payment("7", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"));
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    // unhappy: edit status with invalid status
    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = new Payment("8", PaymentMethod.VOUCHER_CODE.getValue(), Map.of(PaymentMethod.VOUCHER_CODE.getValue(), "ESHOP1234ABC5678"));
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }
}