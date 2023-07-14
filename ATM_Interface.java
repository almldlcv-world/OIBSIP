import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String userId;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId) {
        this.userId = userId;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            destinationAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
        System.out.println();
    }
}

class ATM {
    private List<User> users;
    private Account currentAccount;

    public ATM() {
        users = new ArrayList<>();
        // Add sample users for testing
        users.add(new User("Totan", "1234"));
        users.add(new User("Bar", "5678"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("!!!!Welcome to the ATM!!!!");

        while (true) {
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            System.out.print("PASSWORD: ");
            String pin = scanner.nextLine();

            User user = authenticateUser(userId, pin);

            if (user != null) {
                System.out.println("Authentication successful!!!");
                currentAccount = new Account(userId);
                showMenu(scanner);
                break;
            } else {
                System.out.println("Invalid user ID or PASSWORD. Please try again....");
            }
        }
    }

    private User authenticateUser(String userId, String pin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) {
                return user;
            }
        }
        return null;
    }

    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Transaction history");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currentAccount.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); 
                    currentAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    currentAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter destination account user ID: ");
                    String destinationUserId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();
                    Account destinationAccount = findAccount(destinationUserId);
                    if (destinationAccount != null) {
                        currentAccount.transfer(destinationAccount, transferAmount);
                    } else {
                        System.out.println("Destination account not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!!!!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private Account findAccount(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return new Account(userId);
            }
        }
        return null;
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
