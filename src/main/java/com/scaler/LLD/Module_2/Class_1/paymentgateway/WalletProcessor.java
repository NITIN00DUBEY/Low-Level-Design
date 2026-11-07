package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/**
 * OCP: the "new requirement" payment method. Adding it needed only this class
 * plus one line in the composition root -- PaymentGateway was not modified.
 */
public class WalletProcessor implements PaymentProcessor, RefundProcessor {
    private double balance;

    public WalletProcessor(double balance) {
        this.balance = balance;
    }

    @Override
    public String method() {
        return "WALLET";
    }

    @Override
    public PaymentResult pay(PaymentRequest request) {
        if (request.getAmount() > balance) {
            return new PaymentResult(PaymentStatus.FAILED, "Insufficient wallet balance " + balance);
        }
        balance -= request.getAmount();
        return new PaymentResult(PaymentStatus.SUCCESS, "Debited from wallet, balance " + balance);
    }

    @Override
    public PaymentResult refund(Transaction transaction) {
        balance += transaction.getRequest().getAmount();
        return new PaymentResult(PaymentStatus.REFUNDED, "Credited back to wallet, balance " + balance);
    }
}
