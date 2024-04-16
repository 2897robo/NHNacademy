import java.math.BigDecimal;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;

    private static String nextAccountNumber = "0";

    public BankAccount(String ownerName, BigDecimal balance) {
        this.accountNumber = createAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }

    private static String createAccountNumber() {
        int accountNumber = Integer.parseInt(nextAccountNumber);
        nextAccountNumber = Integer.toString(++accountNumber);
        return nextAccountNumber;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public boolean withDraw(BigDecimal amount) {
        if (amount.compareTo(this.balance) == 1 || amount.compareTo(this.balance) == 0) {
            return false;
        } else {
            balance = balance.subtract(amount);
            return true;
        }
    }

    public void printAccount() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Owner Name: " + this.ownerName);
        System.out.println("Balance: " + this.balance.toString());
    }
}
