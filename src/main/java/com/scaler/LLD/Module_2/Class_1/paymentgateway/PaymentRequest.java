package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** Immutable input to the gateway. */
public final class PaymentRequest {
    private final String payerId;
    private final double amount;
    private final String method;

    public PaymentRequest(String payerId, double amount, String method) {
        this.payerId = payerId;
        this.amount = amount;
        this.method = method;
    }

    public String getPayerId() {
        return payerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return method + " Rs." + amount + " by " + payerId;
    }
}
