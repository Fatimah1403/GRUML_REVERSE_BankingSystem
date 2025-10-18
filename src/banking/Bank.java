package banking;

import banking.accounts.*;
import banking.customer.Customer;
import banking.services.AccountStatementGenerator;
import banking.transactions.TransactionProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private String bankName;
    private String bankCode;
    private Map<String, Customer> customers;
    private Map<String, BankAccount> accounts;
    private TransactionProcessor transactionProcessor;
    private AccountStatementGenerator statementGenerator;
    private int nextAccountNumber;
    private int nextCustomerId;

    public Bank(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.customers = new HashMap<>();
        this.accounts = new HashMap<>();
        this.transactionProcessor = new TransactionProcessor();
        this.statementGenerator = new AccountStatementGenerator();
        this.nextAccountNumber = 100000;
        this.nextCustomerId = 1000;
    }

    public Customer registerCustomer(String firstName, String lastName) {
        String customerId = "CUST" + (++nextCustomerId);
        Customer customer = new Customer(customerId, firstName, lastName);
        customers.put(customerId, customer);
        System.out.println("Registered customer: " + customer.getFullName() + " [" + customerId + "]");
        return customer;
    }

    public BankAccount openCheckingAccount(Customer customer, double initialDeposit) {
        String accountNumber = bankCode + (++nextAccountNumber);
        CheckingAccount account = new CheckingAccount(accountNumber, initialDeposit);
        accounts.put(accountNumber, account);
        customer.addAccount(account);
        System.out.println("Opened Checking Account: " + accountNumber);
        return account;
    }

    public BankAccount openSavingsAccount(Customer customer, double initialDeposit) {
        String accountNumber = bankCode + (++nextAccountNumber);
        SavingsAccount account = new SavingsAccount(accountNumber, initialDeposit);
        accounts.put(accountNumber, account);
        customer.addAccount(account);
        System.out.println("Opened Savings Account: " + accountNumber);
        return account;
    }

    public BankAccount openMoneyMarketAccount(Customer customer, double initialDeposit) {
        String accountNumber = bankCode + (++nextAccountNumber);
        MoneyMarketAccount account = new MoneyMarketAccount(accountNumber, initialDeposit);
        accounts.put(accountNumber, account);
        customer.addAccount(account);
        System.out.println("Opened Money Market Account: " + accountNumber);
        return account;
    }

    public BankAccount openBusinessAccount(Customer customer, double initialDeposit) {
        String accountNumber = bankCode + (++nextAccountNumber);
        BusinessAccount account = new BusinessAccount(accountNumber, initialDeposit);
        accounts.put(accountNumber, account);
        customer.addAccount(account);
        System.out.println("Opened Business Account: " + accountNumber);
        return account;
    }

    public LoanAccount issueLoan(Customer customer, double loanAmount, double interestRate, int termMonths) {
        String accountNumber = bankCode + "L" + (++nextAccountNumber);
        LoanAccount loan = new LoanAccount(accountNumber, loanAmount, interestRate, termMonths);
        accounts.put(accountNumber, loan);
        customer.addAccount(loan);
        System.out.println("Issued Loan: " + accountNumber + " for $" + loanAmount);
        return loan;
    }

    public void performDeposit(BankAccount account, double amount) {
        try {
            transactionProcessor.processDeposit(account, amount);
            System.out.println("Deposited $" + amount + " to " + account.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    public void performWithdrawal(BankAccount account, double amount) {
        try {
            transactionProcessor.processWithdrawal(account, amount);
            System.out.println("Withdrew $" + amount + " from " + account.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    public void performTransfer(BankAccount from, BankAccount to, double amount) {
        try {
            transactionProcessor.processTransfer(from, to, amount);
            System.out.println("Transferred $" + amount + " from " + from.getAccountNumber() +
                    " to " + to.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    public void processMonthEnd() {
        System.out.println("\n=== Processing Month End ===");
        for (BankAccount account : accounts.values()) {
            // Apply interest
            transactionProcessor.applyMonthlyInterest(account);
            // Apply fees
            transactionProcessor.applyMonthlyFees(account);
        }
        System.out.println("Month end processing complete\n");
    }

    public void generateStatement(Customer customer, BankAccount account) {
        String statement = statementGenerator.generateStatement(
                customer, account,
                java.time.LocalDate.now().minusMonths(1),
                java.time.LocalDate.now()
        );
        statementGenerator.printStatement(statement);
    }

    public Customer getCustomer(String customerId) { return customers.get(customerId); }
    public BankAccount getAccount(String accountNumber) { return accounts.get(accountNumber); }
    public int getTotalCustomers() { return customers.size(); }
    public int getTotalAccounts() { return accounts.size(); }
}
