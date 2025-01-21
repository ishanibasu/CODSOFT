import java.util.Scanner;
class BankAcc {
    String accHolder;
    int accNo;
    double balance;

    BankAcc(String name, int num, double bal) {
        accHolder = name;
        accNo = num;
        balance = bal;
    }

    void deposit(double amount) {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("\nDeposited: " + amount);
        } 
        else 
        {
            System.out.println("\nInvalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println("\nWithdrawn: " + amount);
        } 
        else if(amount>balance)
        {
            System.out.println("\nInsufficient balance.");
        }
        else 
        {
        	System.out.println("\nInvalid amount.");
        }
    }

    void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance);
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ATM\n");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Number: ");
        int num = sc.nextInt();
        System.out.print("Enter Balance: ");
        double bal = sc.nextDouble();
        BankAcc account = new BankAcc(name, num, bal);
        
        while (true) {
        	System.out.println("\nCHOOSE AN OPTION:");
        	System.out.println("1. Withdraw");
        	System.out.println("2. Deposit");
        	System.out.println("3. Check Balance");
        	System.out.println("4. Exit");
        	
        	System.out.print("Enter your choice: ");
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmt = sc.nextDouble();
                    account.withdraw(withdrawAmt);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmt = sc.nextDouble();
                    account.deposit(depositAmt);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("\nThank you for using the ATM. See you soon!");
                    sc.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}
