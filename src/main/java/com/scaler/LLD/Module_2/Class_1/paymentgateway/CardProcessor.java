package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** Simulates a card network that declines above the card's limit. */
public class CardProcessor implements PaymentProcessor, RefundProcessor {
    private final double cardLimit;

    public CardProcessor(double cardLimit) {
        this.cardLimit = cardLimit;
    }

    @Override
    public String method() {
        return "CARD";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        if (request.getAmount() > cardLimit) {
            return new PaymentResult(PaymentStatus.FAILED, "Declined by bank: card limit " + cardLimit);
        }
        return new PaymentResult(PaymentStatus.SUCCESS, "Charged to card");
    }

    @Override
    public PaymentResult refund(Transaction transaction) {
        return new PaymentResult(PaymentStatus.REFUNDED, "Reversed to card (5-7 days)");
    }
}
