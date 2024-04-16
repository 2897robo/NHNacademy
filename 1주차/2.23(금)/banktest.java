class banktest {
    public static void main(String[] args) {
        bank account1 = new bank();
        bank account2 = new bank();

        bank.setInterest(3);
        
        account1.deposit(100);
        account1.deposit(50);
        account1.withdraw(30);

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
    }
}