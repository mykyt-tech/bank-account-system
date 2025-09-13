public class SavingsAccount extends BankAccount {
    private float interestRate;

    public SavingsAccount(long accountNumber, String ownerName, String accountType) {
        super(accountNumber, ownerName, accountType);
        this.interestRate = 0;
    }

    public SavingsAccount(long accountNumber, String ownerName, String accountType, float interestRate) {
        super(accountNumber, ownerName, accountType);
        this.interestRate = interestRate;
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

    public float getInterestRate() {
        return interestRate;
    }

    public boolean applyInterest(float interestRate) {
        this.interestRate = interestRate;
        return true;
    }
}
