package com.scaler.LLD.Module_2.Class_1.paymentgateway;

public class PayerValidator implements PaymentValidator {
    @Override
    public void validate(PaymentRequest request) throws PaymentException {
        if (request.getPayerId() == null || request.getPayerId().isBlank()) {
            throw new PaymentException("Payer id is required");
        }
    }
}
