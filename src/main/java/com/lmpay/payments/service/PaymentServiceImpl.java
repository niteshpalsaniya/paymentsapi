package com.lmpay.payments.service;

import com.lmpay.payments.entity.Payment;
import com.lmpay.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id " + id));
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
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

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id " + id));

        paymentRepository.delete(existingPayment);
    }

    @Override
    public List<Payment> searchPayments(String bankName, String sendingCurrency, String receivingCurrency) {
        return paymentRepository.findByBankNameAndSendingCurrencyAndReceivingCurrency(bankName, sendingCurrency, receivingCurrency);
    }
}
