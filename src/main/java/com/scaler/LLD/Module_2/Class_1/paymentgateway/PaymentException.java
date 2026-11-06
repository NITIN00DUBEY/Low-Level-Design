package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/**
 * Checked on purpose (Advanced Java - 4): the caller can recover --
 * fix the amount, pick another payment method, and retry.
 */
public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}
