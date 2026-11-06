package com.scaler.LLD.Module_2.Class_1.paymentgateway;

import java.util.ArrayList;
import java.util.List;

/**
 * ANTI-PATTERN -- what the homework should NOT look like. Violates all five:
 * S: validates, charges, stores, and notifies in one monster method
 * O: every new method = another else-if in pay() AND refund()
 * L: COD "supports" refund() but throws -- callers must special-case it
 * I: one fat class; no way to say "this method can't refund"
 * D: hard-wired to an ArrayList and System.out -- can't swap DB or notifier
 */
public class BadPaymentService {
    private final List<String> db = new ArrayList<>();

    public String pay(String method, String payerId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("bad amount");
        String status;
        if (method.equals("UPI")) {
            status = "SUCCESS";
        } else if (method.equals("CARD")) {
            status = amount > 50_000 ? "FAILED" : "SUCCESS";
        } else if (method.equals("COD")) {
            status = "PENDING";
        } else {
            throw new IllegalArgumentException("unknown method " + method);
        }
        db.add(method + ":" + payerId + ":" + amount + ":" + status);
        System.out.println("    [sms " + payerId + "] payment " + status);
        return status;
    }

    public String refund(String method) {
        if (method.equals("UPI") || method.equals("CARD")) {
            return "REFUNDED";
        } else if (method.equals("COD")) {
            throw new UnsupportedOperationException("COD cannot be refunded");
        }
        throw new IllegalArgumentException("unknown method " + method);
    }
}
