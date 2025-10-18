package banking.accounts;

import banking.transactions.Transaction;
import banking.transactions.TransactionType;
import java.time.LocalDateTime;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private int freeTransactions;
    private int transactionCount;

    public CheckingAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
        this.accountType = "CHECKING";
        this.overdraftLimit = 500.00;
        this.freeTransactions = 10;
        this.transactionCount = 0;
        this.minimumBalance = 100.00;
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) throw new Exception("Invalid amount");
        if (balance - amount < -overdraftLimit) {
            throw new Exception("Exceeds overdraft limit");
        }
        balance -= amount;
        transactionCount++;
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
        return 0; // No interest on checking accounts
    }

    @Override
    public double getMonthlyFee() {
        double fee = 0;
        if (balance < minimumBalance) fee += 15.00;
        if (transactionCount > freeTransactions) {
            fee += (transactionCount - freeTransactions) * 2.50;
        }
        transactionCount = 0; // Reset for new month
        return fee;
    }
}
