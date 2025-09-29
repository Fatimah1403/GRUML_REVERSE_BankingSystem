package test;

import banking.*;

public class TestBanking {
    public static void main(String[] args) {
        // Create bank
        Bank bank = new Bank("City Bank");

        // Create customer
        Customer customer = new Customer("C001", "John Doe");

        // Add customer to bank
        bank.addCustomer(customer);

        // Create and open checking account
        CheckingAccount checking = new CheckingAccount("ACC001", customer, 500.0);
        bank.openAccount(checking);

        // Test withdrawal
        checking.withdraw(100);

        // Create and open savings account
        SavingsAccount savings = new SavingsAccount("ACC002", customer, 0.05);
        bank.openAccount(savings);

        // Test deposit and interest
        savings.deposit(1000);
        savings.applyInterest();
    }
}
