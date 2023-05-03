package com.lmpay.payments.controller;

import com.lmpay.payments.entity.Payment;
import com.lmpay.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    //http://localhost:9090/payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    //http://localhost:9090/payments/"id"
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id " + id));
        return ResponseEntity.ok(payment);
    }
    //http://localhost:9090/payments
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }
    //http://localhost:9090/payments/"id"
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id " + id));
        existingPayment.setReferenceNo(payment.getReferenceNo());
        existingPayment.setTxnDate(payment.getTxnDate());
        existingPayment.setTxnType(payment.getTxnType());
        existingPayment.setBankName(payment.getBankName());
        existingPayment.setRoutingCode(payment.getRoutingCode());
        existingPayment.setSendingCurrency(payment.getSendingCurrency());
        existingPayment.setReceivingCurrency(payment.getReceivingCurrency());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setRetryAttempts(payment.getRetryAttempts());
        existingPayment.setBankRequest(payment.getBankRequest());
        existingPayment.setBankResponse(payment.getBankResponse());
        existingPayment.setTransactionStatus(payment.getTransactionStatus());
        existingPayment.setResponseTime(payment.getResponseTime());
        existingPayment.setCreatedBy(payment.getCreatedBy());
        existingPayment.setCreatedOn(payment.getCreatedOn());
        existingPayment.setUpdatedBy(payment.getUpdatedBy());
        existingPayment.setUpdatedOn(payment.getUpdatedOn());
        existingPayment.setBankCode(payment.getBankCode());
        existingPayment.setBankMtosName(payment.getBankMtosName());

        Payment savedPayment = paymentRepository.save(existingPayment);
        return ResponseEntity.ok(savedPayment);
    }
    //http://localhost:9090/payments/"id"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id " + id));
        paymentRepository.delete(payment);
        return ResponseEntity.noContent().build();
    }
}
