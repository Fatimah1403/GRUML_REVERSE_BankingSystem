package banking;

public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected Customer owner;

    public BankAccount(String accountNumber, Customer owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    public abstract void withdraw(double amount);
    public abstract String getAccountType();

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
