import java.math.BigDecimal;

public class bank {
    int accountNo;
    String ownerName;
    //private int dollar;
    //private int cent;
    //private BigDecimal balance;
    private static int balance;
    private int interest;

    public int getInterest() {
        return this.interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public void setOwnerName(String name) {

    }

    public void deposit(String amount) {
        BigDecimal value = new BigDecimal(amount);
        this.balance = value;

        String[] am = amount.split(".");
        this.dollar += Integer.parseInt(am[0]);
        this.cent += Integer.parseInt(am[1]);
    }

    public void withdraw(int amount) {

    }

    public String getBalance() {
        return BigDecimal.valueOf(this.balance);

        return this.dollar + "." + cent;
    }
}