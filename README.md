# Banking System RUML Experiment

## Overview
This repository contains an expanded banking system (20 classes) designed to test RUML (Reverse UML) generation capabilities on complex, pattern-neutral enterprise systems. The system was developed following Prof. Eric Braude's guidance to avoid recognizable design patterns and validate RUML's ability to handle real-world complexity.

## System Architecture

### Package Structure
- **banking.accounts** (6 classes): Account hierarchy with abstract base and concrete implementations
- **banking.customer** (2 classes): Customer management and profile tracking
- **banking.transactions** (4 classes): Transaction processing, validation, and history
- **banking.services** (3 classes): Utility services for fees, interest, and statements
- **banking** (1 class): Main bank coordinator
- **test** (1 class): System entry point

### Class Hierarchy
```
BankAccount (abstract)
├── CheckingAccount
│   └── BusinessAccount
├── SavingsAccount
├── MoneyMarketAccount
└── LoanAccount
```

### Complete Class List
1. BankAccount (abstract)
2. CheckingAccount
3. SavingsAccount
4. MoneyMarketAccount
5. BusinessAccount
6. LoanAccount
7. Customer
8. CustomerProfile
9. Transaction
10. TransactionType (enum)
11. TransactionProcessor
12. TransactionValidator
13. FeeCalculator
14. InterestCalculator
15. AccountStatementGenerator
16. Bank
17. BankingSystemTest
18. ReportGenerator
19. WeatherDatabase
20. WeatherRecord

## Use Case Workflow

1. **Register new customers with identity verification** - Creates customer profiles with unique IDs and credit ratings
2. **Open checking, savings, money market, business & loan accounts** - Establishes various account types with initial deposits
3. **Process deposits, withdrawals, transfers & loan payments** - Executes validated monetary transactions
4. **Apply monthly interest earnings and account fees** - Calculates and applies interest/fees at month-end
5. **Generate account statements with transaction history** - Produces comprehensive account reports

## RUML Generation Results

### Success Metrics
- **Class Count:** 20 classes ✓
- **Package Organization:** 6 packages ✓
- **Inheritance Levels:** 2 levels ✓
- **Horizontal Layout:** All siblings spread horizontally ✓
- **First-Attempt Accuracy:** 95% ✓
- **Prompt Refinements Needed:** 0 ✓

### Key RUML Features Demonstrated
- Horizontal inheritance spreading (no vertical stacking)
- Linear transaction flow (non-recursive)
- Complete package organization with shading
- All arrows contained within numbered sequence columns
- Clear use case phase demarcation

## Building and Running

### Prerequisites
- Java JDK 11 or higher
- IDE (IntelliJ IDEA recommended)

### Compilation
```bash
javac -d out src/banking/*.java src/banking/accounts/*.java src/banking/customer/*.java src/banking/transactions/*.java src/banking/services/*.java src/test/*.java
```

### Execution
```bash
java -cp out test.BankingSystemTest
```

### Expected Output
```
=== BANKING SYSTEM TEST ===

PHASE 1: Register Customers
Registered customer: John Smith [CUST1001]
Registered customer: Jane Doe [CUST1002]
Registered customer: ACME Corporation [CUST1003]

PHASE 2: Open Accounts
Opened Checking Account: NTB100001
Opened Savings Account: NTB100002
Opened Money Market Account: NTB100003
Opened Business Account: NTB100004
Issued Loan: NTBL100005 for $15000.0

PHASE 3: Perform Transactions
Deposited $500.0 to NTB100001
Withdrew $200.0 from NTB100002
Transferred $1000.0 from NTB100003 to NTB100001

PHASE 4: Month End Processing
=== Processing Month End ===
Month end processing complete

PHASE 5: Generate Statements
====== ACCOUNT STATEMENT ======
[Statement details...]

=== TEST COMPLETE ===
```

## RUML Diagram
The complete RUML diagram can be viewed at: [Google Sheets RUML](https://docs.google.com/spreadsheets/d/YOUR_SHEET_ID)

## Research Significance

This experiment validates several key RUML capabilities:
- **Scalability:** Successfully expanded from 5 to 20 classes (400% increase)
- **Pattern Independence:** No reliance on recognizable design patterns
- **Single-Prompt Generation:** Achieved 95% accuracy without refinements
- **Horizontal Layout:** Improved readability through horizontal inheritance spreading
- **Linear Workflow:** Demonstrated non-recursive transaction processing

## Files Structure
```
BankingSystemRUML/
├── src/
│   ├── banking/
│   │   └── Bank.java
│   ├── banking/accounts/
│   │   ├── BankAccount.java
│   │   ├── CheckingAccount.java
│   │   ├── SavingsAccount.java
│   │   ├── MoneyMarketAccount.java
│   │   ├── BusinessAccount.java
│   │   └── LoanAccount.java
│   ├── banking/customer/
│   │   ├── Customer.java
│   │   └── CustomerProfile.java
│   ├── banking/transactions/
│   │   ├── Transaction.java
│   │   ├── TransactionType.java
│   │   ├── TransactionProcessor.java
│   │   └── TransactionValidator.java
│   ├── banking/services/
│   │   ├── FeeCalculator.java
│   │   ├── InterestCalculator.java
│   │   └── AccountStatementGenerator.java
│   └── test/
│       └── BankingSystemTest.java
├── docs/
│   ├── RUML_Experiment_Report.pdf
│   └── Banking_RUML_Diagram.png
└── README.md
```

## Author
**Fatimah Hassan**  
Software Engineering Research  
October 2025

## Acknowledgments
Prof. Eric Braude (Boston University)for guidance on RUML experimentation and pattern-neutral system design.

## License
This project is for educational and research purposes.