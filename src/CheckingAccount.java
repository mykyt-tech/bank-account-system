public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(long accountNumber, String ownerName, String accountType) {
        super(accountNumber, ownerName, accountType);
        this.overdraftLimit = 500;
    }

    public CheckingAccount(long accountNumber, String ownerName, String accountType, float overdraftLimit) {
        super(accountNumber, ownerName, accountType);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    @Override
    public double withdraw(double amount) {
        if (balance >= amount) {
            double temp = balance;
            balance -= amount;
            return temp;
        }

        return -1;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return true;
    }
}
