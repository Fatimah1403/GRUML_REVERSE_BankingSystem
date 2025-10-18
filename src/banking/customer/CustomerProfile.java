package banking.customer;

import java.time.LocalDate;

public class CustomerProfile {
    private String customerId;
    private String creditRating;
    private LocalDate memberSince;
    private String customerType; // REGULAR, PREMIUM, VIP
    private int accountCount;
    private double totalRelationshipValue;

    public CustomerProfile(String customerId) {
        this.customerId = customerId;
        this.creditRating = "GOOD";
        this.memberSince = LocalDate.now();
        this.customerType = "REGULAR";
        this.accountCount = 0;
    }

    public void updateCreditRating(String rating) {
        this.creditRating = rating;
    }

    public void upgradeCustomerType() {
        if (totalRelationshipValue > 100000) {
            customerType = "VIP";
        } else if (totalRelationshipValue > 25000) {
            customerType = "PREMIUM";
        }
    }

    public void updateAccountCount(int count) {
        this.accountCount = count;
    }

    public void updateRelationshipValue(double value) {
        this.totalRelationshipValue = value;
        upgradeCustomerType();
    }

    public String getCustomerType() { return customerType; }
    public String getCreditRating() { return creditRating; }
    public LocalDate getMemberSince() { return memberSince; }
}
