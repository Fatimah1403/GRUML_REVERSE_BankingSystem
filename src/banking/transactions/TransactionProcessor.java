package banking.transactions;

import banking.accounts.BankAccount;
import banking.services.FeeCalculator;
import banking.services.InterestCalculator;

public class TransactionProcessor {
    private FeeCalculator feeCalculator;
    private InterestCalculator interestCalculator;
    private TransactionValidator validator;

    public TransactionProcessor() {
        this.feeCalculator = new FeeCalculator();
        this.interestCalculator = new InterestCalculator();
        this.validator = new TransactionValidator();
    }

    public void processDeposit(BankAccount account, double amount) throws Exception {
        if (!validator.validateDeposit(amount)) {
            throw new Exception("Invalid deposit amount");
        }
        account.deposit(amount);
    }

    public void processWithdrawal(BankAccount account, double amount) throws Exception {
        if (!validator.validateWithdrawal(account, amount)) {
            throw new Exception("Invalid withdrawal");
        }
        account.withdraw(amount);
    }

    public void processTransfer(BankAccount from, BankAccount to, double amount) throws Exception {
        if (!validator.validateTransfer(from, to, amount)) {
            throw new Exception("Invalid transfer");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }

    public void applyMonthlyInterest(BankAccount account) {
        double interest = interestCalculator.calculate(account);
        if (interest > 0) {
            account.deposit(interest);
        }
    }

    public void applyMonthlyFees(BankAccount account) {
        double fee = feeCalculator.calculate(account);
        if (fee > 0) {
            try {
                account.withdraw(fee);
            } catch (Exception e) {
                System.out.println("Fee application failed: " + e.getMessage());
            }
        }
    }
}