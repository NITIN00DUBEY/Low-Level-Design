package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** Supports pay + refund, so it implements both small interfaces. */
public class UpiProcessor implements PaymentProcessor, RefundProcessor {
    @Override
    public String method() {
        return "UPI";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(PaymentStatus.SUCCESS, "Collected via UPI");
    }

    @Override
    public PaymentResult refund(Transaction transaction) {
        return new PaymentResult(PaymentStatus.REFUNDED, "Refunded to UPI account");
    }
}
