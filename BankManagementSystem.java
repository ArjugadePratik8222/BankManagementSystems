import java.util.ArrayList;
import java.util.Scanner;

// Interface for basic operations
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

// BankAccount class implementing the interface
class BankAccount implements BankOperations {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: ₹" + amount);
        } else {
            System.out.println("Invalid or insufficient funds!");
        }
    }

    @Override
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

// Main class
public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        System.out.println("=== Bank Account Management System ===");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter account number: ");
                    String accNum = sc.nextLine();
                    System.out.print("Enter initial balance: ₹");
                    double balance = sc.nextDouble();
                    accounts.add(new BankAccount(name, accNum, balance));
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String depAccNum = sc.nextLine();
                    BankAccount depAcc = findAccount(accounts, depAccNum);
                    if (depAcc != null) {
                        System.out.print("Enter deposit amount: ₹");
                        depAcc.deposit(sc.nextDouble());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    String withAccNum = sc.nextLine();
                    BankAccount withAcc = findAccount(accounts, withAccNum);
                    if (withAcc != null) {
                        System.out.print("Enter withdraw amount: ₹");
                        withAcc.withdraw(sc.nextDouble());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    String balAccNum = sc.nextLine();
                    BankAccount balAcc = findAccount(accounts, balAccNum);
                    if (balAcc != null) {
                        balAcc.checkBalance();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static BankAccount findAccount(ArrayList<BankAccount> accounts, String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}
