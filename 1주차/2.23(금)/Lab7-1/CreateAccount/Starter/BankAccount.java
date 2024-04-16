import java.math.*;
import java.util.*;

public class BankAccount {
    private long accountNumber;
    private String ownerName;
    private BigDecimal balance;

    private static long nextAccountNumber;

    public static long nextNumber() {
        return nextAccountNumber++;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public boolean withDraw(BigDecimal amount) {
        if (amount.compareTo(this.balance) == 1 || amount.compareTo(this.balance) == 0) {
            return false;
        }
        else {
            balance = balance.subtract(amount);
            return true;
        }
    }

    //
    // add setData method here.
    //
    public void setDate(String ownerName, BigDecimal balance) {
        this.accountNumber = nextNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }

    //
    // add getNumber method here.
    //
    public long getAccountNum() {
        return this.accountNumber;
    }

    public String getownerName() {
        return this.ownerName;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}

class CreateAccount {
    public static BankAccount createNewBankAccount(String ownerName, BigDecimal balance) {
        BankAccount newAccount= new BankAccount();

        newAccount.setDate(ownerName, balance);

        return newAccount;
    }

    public static void TestDeposit(BankAccount account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        BigDecimal amount = new BigDecimal(scanner.next());
        account.deposit(amount);
    }

    public static void TestWithDraw(BankAccount account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        BigDecimal amount = new BigDecimal(scanner.next());
        if(!account.withDraw(amount)) {
            System.out.println("Insufficient funds!");
        }
    }

    public static void main(String[] args) {
        BankAccount bankAccount1 = CreateAccount.createNewBankAccount("Vesper Lind", new BigDecimal("0.0"));
        BankAccount bankAccount2 = CreateAccount.createNewBankAccount("Vesper Lind", new BigDecimal("0.0"));

        printBankAccount(bankAccount1);
        TestDeposit(bankAccount1);
        printBankAccount(bankAccount1);
        TestWithDraw(bankAccount1);
        printBankAccount(bankAccount1);
        
        printBankAccount(bankAccount2);
        TestDeposit(bankAccount2);
        printBankAccount(bankAccount2);
        TestWithDraw(bankAccount2);
        printBankAccount(bankAccount2);
        
    }

    public static void printBankAccount(BankAccount account) {
        System.out.println("Account Number: " + account.getAccountNum());
        System.out.println("Owner Name: " + account.getownerName());
        System.out.println("Balance: " + account.getBalance());
    }
}