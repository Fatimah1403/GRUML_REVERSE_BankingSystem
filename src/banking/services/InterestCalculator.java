package banking.services;

import banking.accounts.*;

public class InterestCalculator {

    public double calculate(BankAccount account) {
        return account.calculateInterest();
    }

    public double calculateCompoundInterest(double principal, double rate, int periods) {
        return principal * Math.pow(1 + rate / periods, periods) - principal;
    }

    public double calculateAPY(double rate, int compoundingPeriods) {
        return Math.pow(1 + rate / compoundingPeriods, compoundingPeriods) - 1;
    }
}