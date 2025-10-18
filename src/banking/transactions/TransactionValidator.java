package banking.transactions;

import banking.accounts.BankAccount;

public class TransactionValidator {
    private static final double MAX_SINGLE_TRANSACTION = 100000.00;
    private static final double MIN_TRANSACTION = 0.01;

    public boolean validateDeposit(double amount) {
        return amount >= MIN_TRANSACTION && amount <= MAX_SINGLE_TRANSACTION;
    }

    public boolean validateWithdrawal(BankAccount account, double amount) {
        if (amount < MIN_TRANSACTION || amount > MAX_SINGLE_TRANSACTION) {
            return false;
        }
        // Additional validation could check daily limits, etc.
        return true;
    }

    public boolean validateTransfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null || from.equals(to)) {
            return false;
        }
        return validateWithdrawal(from, amount);
    }

    public boolean validateAccountStatus(BankAccount account) {
        return account != null && account.isActive();
    }
}