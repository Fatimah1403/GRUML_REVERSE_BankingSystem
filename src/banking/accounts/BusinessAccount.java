package banking.accounts;

import banking.transactions.Transaction;
import banking.transactions.TransactionType;
import java.time.LocalDateTime;

public class BusinessAccount extends CheckingAccount {
    private double creditLine;
    private double cashBackRate;
    private double monthlyRevenue;

    public BusinessAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
        this.accountType = "BUSINESS";
        this.creditLine = 10000.00;
        this.cashBackRate = 0.02; // 2% cash back
        this.freeTransactions = 100;
        this.minimumBalance = 5000.00;
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) throw new Exception("Invalid amount");
        if (balance - amount < -creditLine) {
            throw new Exception("Exceeds credit line");
        }
        balance -= amount;
        transactionCount++;
        addTransaction(new Transaction(
                TransactionType.WITHDRAWAL, amount, LocalDateTime.now(), accountNumber
        ));
    }

    public void recordRevenue(double amount) {
        monthlyRevenue += amount;
        deposit(amount);
    }

    public double calculateCashBack() {
        double cashBack = monthlyRevenue * cashBackRate;
        monthlyRevenue = 0; // Reset for new month
        return cashBack;
    }

    @Override
    public double getMonthlyFee() {
        double fee = balance < minimumBalance ? 50.00 : 0;
        if (transactionCount > freeTransactions) {
            fee += (transactionCount - freeTransactions) * 0.50;
        }
        transactionCount = 0;
        return fee;
    }
}