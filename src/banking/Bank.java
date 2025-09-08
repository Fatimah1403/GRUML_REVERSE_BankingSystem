package banking;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
    private List<BankAccount> accounts;
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void openAccount(BankAccount account) {
        accounts.add(account);
        account.owner.setAccount(account);
    }

    public Customer findCustomer(String customerId) {
        // Find customer logic
        return null;
    }
}