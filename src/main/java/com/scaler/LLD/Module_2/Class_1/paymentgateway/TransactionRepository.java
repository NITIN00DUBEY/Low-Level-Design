package com.scaler.LLD.Module_2.Class_1.paymentgateway;

import java.util.Optional;

/** DIP: the gateway depends on this abstraction, not on a specific database. */
public interface TransactionRepository {
    void save(Transaction transaction);

    Optional<Transaction> findById(String id);
}
