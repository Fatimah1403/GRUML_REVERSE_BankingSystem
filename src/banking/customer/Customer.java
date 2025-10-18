package banking.customer;

import banking.accounts.BankAccount;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CustomerProfile profile;
    private List<BankAccount> accounts;

    public Customer(String customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
        this.profile = new CustomerProfile(customerId);
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        profile.updateAccountCount(accounts.size());
    }

    public void removeAccount(BankAccount account) {
        accounts.remove(account);
        profile.updateAccountCount(accounts.size());
    }

    public double getTotalBalance() {
        return accounts.stream().mapToDouble(BankAccount::getBalance).sum();
    }

    public String getCustomerId() { return customerId; }
    public String getFullName() { return firstName + " " + lastName; }
    public List<BankAccount> getAccounts() { return accounts; }
    public CustomerProfile getProfile() { return profile; }

    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
