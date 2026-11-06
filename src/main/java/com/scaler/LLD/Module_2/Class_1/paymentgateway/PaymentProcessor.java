package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/**
 * ISP: the minimum every payment method can honour -- take a payment.
 * Refunds are a separate capability (RefundProcessor) because not every method supports them.
 */
public interface PaymentProcessor {
    /** Key the gateway routes on, e.g. "UPI". */
    String method();

    PaymentResult pay(PaymentRequest request);
}
