package com.lmpay.payments.service;

import com.lmpay.payments.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Payment createPayment(Payment payment);

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);

    List<Payment> searchPayments(String bankName, String sendingCurrency, String receivingCurrency);
}

