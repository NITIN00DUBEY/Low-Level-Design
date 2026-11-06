package com.scaler.LLD.Module_2.Class_1.paymentgateway;

public class AmountValidator implements PaymentValidator {
    private final double maxAmount;

    public AmountValidator(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public void validate(PaymentRequest request) throws PaymentException {
        if (request.getAmount() <= 0) {
            throw new PaymentException("Amount must be positive, got " + request.getAmount());
        }
        if (request.getAmount() > maxAmount) {
            throw new PaymentException("Amount " + request.getAmount() + " exceeds limit " + maxAmount);
        }
    }
}
