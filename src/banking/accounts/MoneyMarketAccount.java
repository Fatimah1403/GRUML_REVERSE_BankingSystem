package banking.accounts;

import banking.transactions.Transaction;
import banking.transactions.TransactionType;
import java.time.LocalDateTime;

public class MoneyMarketAccount extends BankAccount {
    private double interestRate;
    private int transactionLimit;
    private int transactionsThisMonth;

    public MoneyMarketAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
        this.accountType = "MONEY_MARKET";
        this.interestRate = 0.045; // 4.5% annual
        this.transactionLimit = 3;
        this.transactionsThisMonth = 0;
        this.minimumBalance = 2500.00;
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (transactionsThisMonth >= transactionLimit) {
            throw new Exception("Monthly transaction limit reached");
        }
        if (balance - amount < minimumBalance) {
            throw new Exception("Cannot go below minimum balance");
        }
        balance -= amount;
        transactionsThisMonth++;
        addTransaction(new Transaction(
                TransactionType.WITHDRAWAL, amount, LocalDateTime.now(), accountNumber
        ));
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction(new Transaction(
                    TransactionType.DEPOSIT, amount, LocalDateTime.now(), accountNumber
            ));
        }
    }

    @Override
    public double calculateInterest() {
        if (balance >= 10000) {
            return balance * (0.055 / 12); // Premium rate
        }
        return balance * (interestRate / 12);
    }

    @Override
    public double getMonthlyFee() {
        transactionsThisMonth = 0; // Reset
        return balance < minimumBalance ? 25.00 : 0;
    }
}
