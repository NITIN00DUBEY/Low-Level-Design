package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/**
 * LSP + ISP: COD cannot refund through the gateway, so it simply does NOT implement RefundProcessor.
 * Compare Penguin: it doesn't implement Flyable instead of throwing from fly().
 */
public class CashOnDeliveryProcessor implements PaymentProcessor {
    @Override
    public String method() {
        return "COD";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(PaymentStatus.PENDING, "Collect cash at delivery");
    }
}
