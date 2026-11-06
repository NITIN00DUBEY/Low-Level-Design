package com.scaler.LLD.Module_2.Class_1.paymentgateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * High-level orchestrator. Only responsibility: run the payment workflow
 * (validate -> route -> record -> notify).
 *
 * S: validation, persistence, notification and provider calls are delegated to other classes
 * O: new payment method = new PaymentProcessor class; no if-else here to modify
 * L: every PaymentProcessor is used the same way -- no instanceof, no special cases
 * I: pay and refund are separate interfaces; COD isn't forced to implement refund
 * D: depends only on interfaces, all injected through the constructor
 */
public class PaymentGateway {
    private final Map<String, PaymentProcessor> processors = new HashMap<>();
    private final Map<String, RefundProcessor> refundProcessors = new HashMap<>();
    private final List<PaymentValidator> validators;
    private final TransactionRepository repository;
    private final NotificationService notificationService;
    private int nextId = 1;

    public PaymentGateway(List<PaymentProcessor> processors,
                          List<RefundProcessor> refundProcessors,
                          List<PaymentValidator> validators,
                          TransactionRepository repository,
                          NotificationService notificationService) {
        processors.forEach(p -> this.processors.put(p.method(), p));
        refundProcessors.forEach(r -> this.refundProcessors.put(r.method(), r));
        this.validators = List.copyOf(validators);
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Transaction pay(PaymentRequest request) throws PaymentException {
        for (PaymentValidator validator : validators) {
            validator.validate(request);
        }
        PaymentProcessor processor = processors.get(request.getMethod());
        if (processor == null) {
            throw new PaymentException("Unsupported payment method: " + request.getMethod());
        }
        PaymentResult result = processor.pay(request);
        Transaction txn = new Transaction("TXN-" + nextId++, request, result.getStatus(), result.getMessage());
        repository.save(txn);
        notificationService.notify(txn);
        return txn;
    }

    public Transaction refund(String transactionId) throws PaymentException {
        Transaction txn = repository.findById(transactionId)
                .orElseThrow(() -> new PaymentException("No transaction " + transactionId));
        RefundProcessor refunder = refundProcessors.get(txn.getRequest().getMethod());
        if (refunder == null) {
            throw new PaymentException("Refunds not supported for " + txn.getRequest().getMethod());
        }
        if (txn.getStatus() != PaymentStatus.SUCCESS) {
            throw new PaymentException("Only successful payments can be refunded; " + transactionId + " is " + txn.getStatus());
        }
        PaymentResult result = refunder.refund(txn);
        Transaction refunded = txn.withStatus(result.getStatus(), result.getMessage());
        repository.save(refunded);
        notificationService.notify(refunded);
        return refunded;
    }
}
