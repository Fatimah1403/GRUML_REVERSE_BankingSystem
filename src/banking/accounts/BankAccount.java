package banking.accounts;

import banking.transactions.Transaction;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected String accountType;
    protected List<Transaction> transactionHistory;
    protected double minimumBalance;
    protected boolean isActive;

    public BankAccount(String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        this.isActive = true;
    }

    public abstract void withdraw(double amount) throws Exception;
    public abstract void deposit(double amount);
    public abstract double calculateInterest();
    public abstract double getMonthlyFee();

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
    public boolean isActive() { return isActive; }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}