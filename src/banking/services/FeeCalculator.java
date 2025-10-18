package banking.services;

import banking.accounts.*;

public class FeeCalculator {

    public double calculate(BankAccount account) {
        return account.getMonthlyFee();
    }

    public double calculateOverdraftFee(double overdraftAmount) {
        return overdraftAmount > 0 ? 35.00 : 0;
    }

    public double calculateWireTransferFee(boolean domestic) {
        return domestic ? 25.00 : 45.00;
    }

    public double calculateATMFee(boolean inNetwork) {
        return inNetwork ? 0 : 3.00;
    }
}