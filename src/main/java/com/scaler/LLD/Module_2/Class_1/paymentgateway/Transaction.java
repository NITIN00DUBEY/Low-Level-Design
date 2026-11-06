package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** Immutable record of a payment. A refund produces a new Transaction via withStatus(). */
public final class Transaction {
    private final String id;
    private final PaymentRequest request;
    private final PaymentStatus status;
    private final String message;

    public Transaction(String id, PaymentRequest request, PaymentStatus status, String message) {
        this.id = id;
        this.request = request;
        this.status = status;
        this.message = message;
    }

    public Transaction withStatus(PaymentStatus newStatus, String newMessage) {
        return new Transaction(id, request, newStatus, newMessage);
    }

    public String getId() {
        return id;
    }

    public PaymentRequest getRequest() {
        return request;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return id + " [" + status + "] " + request + " -- " + message;
    }
}
