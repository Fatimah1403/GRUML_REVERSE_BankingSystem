package banking;

public class Customer {
    private String customerId;
    private String name; // name of the bank
    private BankAccount account;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public BankAccount getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }
}