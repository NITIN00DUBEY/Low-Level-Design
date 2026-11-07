package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** SRP: each validator checks exactly one rule. OCP: add a rule by adding a class. */
public interface PaymentValidator {
    void validate(PaymentRequest request) throws PaymentException;
}
