public abstract class BankAccount {
    protected final long accountNumber;
    protected final String accountType;
    protected final String ownerName;
    protected double balance;

    public BankAccount(long accountNumber, String ownerName, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.ownerName = ownerName;
        this.balance = 0;
    }

    public abstract boolean deposit(double amount);

    public abstract double withdraw(double amount);

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "Account number: " + accountNumber + ", owner name: " + ownerName + ", balance: " + balance;
    }
}
