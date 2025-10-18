package banking.accounts;

import banking.transactions.Transaction;
import banking.transactions.TransactionType;
import java.time.LocalDateTime;

public class LoanAccount extends BankAccount {
    private double loanAmount;
    private double interestRate;
    private int termMonths;
    private double monthlyPayment;
    private int paymentsMade;

    public LoanAccount(String accountNumber, double loanAmount, double interestRate, int termMonths) {
        super(accountNumber, -loanAmount); // Negative balance for loan
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.monthlyPayment = calculateMonthlyPayment();
        this.paymentsMade = 0;
        this.accountType = "LOAN";
    }

    private double calculateMonthlyPayment() {
        double r = interestRate / 12;
        return (loanAmount * r * Math.pow(1 + r, termMonths)) /
                (Math.pow(1 + r, termMonths) - 1);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        throw new Exception("Cannot withdraw from loan account");
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Reduces loan balance
            paymentsMade++;
            addTransaction(new Transaction(
                    TransactionType.LOAN_PAYMENT, amount, LocalDateTime.now(), accountNumber
            ));
        }
    }

    @Override
    public double calculateInterest() {
        return Math.abs(balance) * (interestRate / 12);
    }

    @Override
    public double getMonthlyFee() {
        return 0; // No fees, just payments
    }

    public double getRemainingBalance() {
        return Math.abs(balance);
    }

    public int getRemainingPayments() {
        return termMonths - paymentsMade;
    }
}