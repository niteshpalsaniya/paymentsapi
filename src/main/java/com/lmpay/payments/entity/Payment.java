package com.lmpay.payments.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.sql.Date;
import java.sql.Time;
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long referenceNo;
    private Date txnDate;
    private String txnType;
    private String bankName;
    private String routingCode;
    private String sendingCurrency;
    private String receivingCurrency;
    private long amount;
    private long retryAttempts;
    private String bankRequest;
    private String bankResponse;
    private String transactionStatus;
    private Time responseTime;
    private String createdBy;
    private String createdOn;
    private String updatedBy;
    private String updatedOn;
    private String bankCode;
    private String bankMtosName;

}
