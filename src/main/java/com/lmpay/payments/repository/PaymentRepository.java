package com.lmpay.payments.repository;

import com.lmpay.payments.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByBankNameAndSendingCurrencyAndReceivingCurrency(String bankName, String sendingCurrency, String receivingCurrency);
}

