package banking.services;

import banking.accounts.BankAccount;
import banking.customer.Customer;
import banking.transactions.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountStatementGenerator {
    private DateTimeFormatter dateFormatter;

    public AccountStatementGenerator() {
        this.dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    }

    public String generateStatement(Customer customer, BankAccount account, LocalDate startDate, LocalDate endDate) {
        StringBuilder statement = new StringBuilder();

        statement.append("====== ACCOUNT STATEMENT ======\n");
        statement.append("Customer: ").append(customer.getFullName()).append("\n");
        statement.append("Account: ").append(account.getAccountNumber()).append("\n");
        statement.append("Period: ").append(startDate.format(dateFormatter))
                .append(" to ").append(endDate.format(dateFormatter)).append("\n");
        statement.append("Starting Balance: $").append(String.format("%.2f", account.getBalance())).append("\n\n");

        statement.append("TRANSACTIONS:\n");
        statement.append("-".repeat(50)).append("\n");

        for (Transaction t : account.getTransactionHistory()) {
            if (!t.getTimestamp().toLocalDate().isBefore(startDate) &&
                    !t.getTimestamp().toLocalDate().isAfter(endDate)) {
                statement.append(t.toString()).append("\n");
            }
        }

        statement.append("-".repeat(50)).append("\n");
        statement.append("Ending Balance: $").append(String.format("%.2f", account.getBalance())).append("\n");
        statement.append("================================\n");

        return statement.toString();
    }

    public void printStatement(String statement) {
        System.out.println(statement);
    }
}