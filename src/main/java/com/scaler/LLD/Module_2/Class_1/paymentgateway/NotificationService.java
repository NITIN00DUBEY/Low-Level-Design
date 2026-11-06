package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** DIP + SRP: notifying the user is not the gateway's job; it just calls this abstraction. */
public interface NotificationService {
    void notify(Transaction transaction);
}
