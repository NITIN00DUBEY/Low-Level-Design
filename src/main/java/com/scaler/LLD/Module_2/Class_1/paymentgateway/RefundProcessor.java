package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** ISP: optional capability -- only methods that can actually refund implement this. */
public interface RefundProcessor {
    String method();

    PaymentResult refund(Transaction transaction);
}
