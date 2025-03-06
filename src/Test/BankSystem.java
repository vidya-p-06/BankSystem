package Test;
import java.io.*;
import java.util.*;

class Account {
    private String accNo;
    private String name;
    private String address;
    private String mobileNo;
    private double balance;
    
    public Account(String accNo, String name, String address, String mobileNo, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.balance = balance;
    }
    
    public void deposit(double amount) {
        if (amount<=0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance=balance+amount;
        System.out.println(amount + " deposited successfully. New Balance is" + balance);
    }
    
    public void withdraw(double amount) {
        if (amount<=0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds!");
        }
        balance= balance-amount;
        System.out.println(amount + " withdrawn successfully. New Balance is " + balance);
    }
    
    public void viewDetails() {
        System.out.println("Account No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Mobile No: " + mobileNo);
        System.out.println("Balance: " + balance);
    }
}

public class BankSystem {
    private static Map<String, Account> accounts = new HashMap<>();
    private static  Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("Bank System Menu.......");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    viewAccount();
                    break;
                case 5:
                    System.out.println("Exiting.");
                  
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void createAccount() {
        System.out.print("Enter Your Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Your Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Your Mobile Number: ");
        String mobileNo = sc.nextLine();
        
        Account account = new Account(accNo, name, address, mobileNo, 0);
        accounts.put(accNo, account);
        System.out.println("Account created successfully!");
    }
    
    private static void deposit() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        Account account = accounts.get(accNo);
        if (account==null) {
            System.out.println("Account not found!");
            return;
        }
        
        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        try {
            account.deposit(amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void withdraw() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        Account account = accounts.get(accNo);
        if (account==null) {
            System.out.println("Account not found!");
            return;
        }
        
        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        try {
            account.withdraw(amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewAccount() {
        System.out.print("Enter Your Account Number: ");
        String accNo = sc.nextLine();
        Account account = accounts.get(accNo);
        if (account==null) {
            System.out.println("Account not found!");
            return;
        }
        account.viewDetails();
    }
}
