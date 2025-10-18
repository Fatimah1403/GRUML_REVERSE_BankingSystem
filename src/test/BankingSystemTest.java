package test;

import banking.*;
import banking.accounts.*;
import banking.customer.Customer;
import java.time.LocalDate;

public class BankingSystemTest {
    public static void main(String[] args) {
        // Initialize bank
        Bank bank = new Bank("National Trust Bank", "NTB");

        System.out.println("=== BANKING SYSTEM TEST ===\n");

        // (i) Register customers
        System.out.println("PHASE 1: Register Customers");
        Customer john = bank.registerCustomer("John", "Smith");
        Customer jane = bank.registerCustomer("Jane", "Doe");
        Customer acmeCorp = bank.registerCustomer("ACME", "Corporation");

        // (ii) Open various accounts
        System.out.println("\nPHASE 2: Open Accounts");
        BankAccount johnChecking = bank.openCheckingAccount(john, 1000);
        BankAccount johnSavings = bank.openSavingsAccount(john, 5000);
        BankAccount janeMoneyMarket = bank.openMoneyMarketAccount(jane, 10000);
        BankAccount acmeBusiness = bank.openBusinessAccount(acmeCorp, 25000);
        LoanAccount johnLoan = bank.issueLoan(john, 15000, 0.065, 36);

        // (iii) Perform transactions
        System.out.println("\nPHASE 3: Perform Transactions");
        bank.performDeposit(johnChecking, 500);
        bank.performWithdrawal(johnSavings, 200);
        bank.performTransfer(janeMoneyMarket, johnChecking, 1000);

        // Business account operations
        if (acmeBusiness instanceof BusinessAccount) {
            ((BusinessAccount) acmeBusiness).recordRevenue(5000);
        }

        // Loan payment
        bank.performDeposit(johnLoan, 500);

        // (iv) Month end processing
        System.out.println("\nPHASE 4: Month End Processing");
        bank.processMonthEnd();

        // (v) Generate statements
        System.out.println("\nPHASE 5: Generate Statements");
        bank.generateStatement(john, johnChecking);
        bank.generateStatement(jane, janeMoneyMarket);

        // Display summary
        System.out.println("\n=== SYSTEM SUMMARY ===");
        System.out.println("Total Customers: " + bank.getTotalCustomers());
        System.out.println("Total Accounts: " + bank.getTotalAccounts());
        System.out.println("John's Total Balance: $" + john.getTotalBalance());
        System.out.println("Jane's Total Balance: $" + jane.getTotalBalance());
        System.out.println("ACME's Total Balance: $" + acmeCorp.getTotalBalance());

        System.out.println("\n=== TEST COMPLETE ===");
    }
}