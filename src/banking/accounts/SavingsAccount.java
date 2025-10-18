package banking.accounts;

import banking.transactions.Transaction;
import banking.transactions.TransactionType;
import java.time.LocalDateTime;

public class SavingsAccount extends BankAccount {
    private double interestRate;
    private int withdrawalsThisMonth;
    private int maxMonthlyWithdrawals;

    public SavingsAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
        this.accountType = "SAVINGS";
        this.interestRate = 0.025; // 2.5% annual
        this.withdrawalsThisMonth = 0;
        this.maxMonthlyWithdrawals = 6;
        this.minimumBalance = 500.00;
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (withdrawalsThisMonth >= maxMonthlyWithdrawals) {
            throw new Exception("Monthly withdrawal limit reached");
        }
        if (balance - amount < minimumBalance) {
            throw new Exception("Cannot go below minimum balance");
        }
        balance -= amount;
        withdrawalsThisMonth++;
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
        return balance * (interestRate / 12); // Monthly interest
    }

    @Override
    public double getMonthlyFee() {
        withdrawalsThisMonth = 0; // Reset for new month
        return balance < minimumBalance ? 10.00 : 0;
    }
}
