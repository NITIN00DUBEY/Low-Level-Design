package com.scaler.LLD.Module_2.Class_1.paymentgateway;

/** Stand-in for SMS / email / push -- each would be its own NotificationService. */
public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notify(Transaction transaction) {
        System.out.println("    [notify " + transaction.getRequest().getPayerId() + "] "
                + transaction.getId() + " is " + transaction.getStatus());
    }
}
