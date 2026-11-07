package com.scaler.LLD.Module_2.Class_1.paymentgateway;

import java.util.List;

/**
 * Homework: Payment Gateway using all 5 SOLID principles.
 * main() is the composition root -- the only place that knows concrete classes (DI).
 */
public class PaymentGatewayDemo {
    public static void main(String[] args) {
        System.out.println("=== Before: BadPaymentService (violates all 5) ===");
        BadPaymentService bad = new BadPaymentService();
        bad.pay("UPI", "rehber", 500);
        try {
            bad.refund("COD");
        } catch (UnsupportedOperationException e) {
            System.out.println("  refund(COD) blew up at runtime: " + e.getMessage());
        }

        System.out.println("\n=== After: SOLID PaymentGateway ===");
        UpiProcessor upi = new UpiProcessor();
        CardProcessor card = new CardProcessor(50_000);
        CashOnDeliveryProcessor cod = new CashOnDeliveryProcessor();
        WalletProcessor wallet = new WalletProcessor(1_000);   // OCP: added later, gateway untouched

        PaymentGateway gateway = new PaymentGateway(
                List.of(upi, card, cod, wallet),
                List.of(upi, card, wallet),                    // COD is not a RefundProcessor (ISP/LSP)
                List.of(new PayerValidator(), new AmountValidator(1_00_000)),
                new InMemoryTransactionRepository(),
                new ConsoleNotificationService());

        PaymentRequest[] requests = {
                new PaymentRequest("rehber", 500, "UPI"),
                new PaymentRequest("aman", 75_000, "CARD"),     // declined by card limit
                new PaymentRequest("aman", 20_000, "CARD"),
                new PaymentRequest("riya", 1_200, "COD"),
                new PaymentRequest("riya", 300, "WALLET"),
                new PaymentRequest("riya", 5_000, "WALLET"),    // insufficient balance
                new PaymentRequest("", 100, "UPI"),             // validator: payer
                new PaymentRequest("kunal", -10, "UPI"),        // validator: amount
                new PaymentRequest("kunal", 2_00_000, "UPI"),   // validator: limit
                new PaymentRequest("kunal", 100, "CRYPTO"),     // unsupported method
        };
        for (PaymentRequest request : requests) {
            pay(gateway, request);
        }

        System.out.println("\n=== Refunds ===");
        for (String id : new String[]{"TXN-1", "TXN-3", "TXN-4", "TXN-5", "TXN-2", "TXN-99", "TXN-1"}) {
            try {
                System.out.println("  refund " + id + " -> " + gateway.refund(id));
            } catch (PaymentException e) {
                System.out.println("  refund " + id + " rejected: " + e.getMessage());
            }
        }
    }

    private static void pay(PaymentGateway gateway, PaymentRequest request) {
        try {
            System.out.println("  pay " + request + " -> " + gateway.pay(request));
        } catch (PaymentException e) {
            System.out.println("  pay " + request + " rejected: " + e.getMessage());
        }
    }
}
