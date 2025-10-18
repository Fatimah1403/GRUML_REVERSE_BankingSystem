package banking.transactions;

import java.time.LocalDateTime;

public class Transaction {
    private static int transactionCounter = 1000;
    private String transactionId;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
    private String accountNumber;
    private String description;

    public Transaction(TransactionType type, double amount, LocalDateTime timestamp, String accountNumber) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.accountNumber = accountNumber;
        this.description = type.toString() + " - " + amount;
    }

    public String getTransactionId() { return transactionId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAccountNumber() { return accountNumber; }

    @Override
    public String toString() {
        return String.format("%s | %s | $%.2f | %s",
                transactionId, type, amount, timestamp);
    }
}
