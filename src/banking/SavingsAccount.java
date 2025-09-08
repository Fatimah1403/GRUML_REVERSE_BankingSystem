package banking;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer owner, double rate) {
        super(accountNumber, owner);
        this.interestRate = rate;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    public void applyInterest() {
        balance *= (1 + interestRate);
    }
}