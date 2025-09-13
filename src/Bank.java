import java.util.ArrayList;

public final class Bank {
    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        BankApp.showMenu();
    }

    public static boolean createAccount(String ownerName, String accountType) {
        if (accountType.equals("savings")) {
            var account = new SavingsAccount(accounts.size() + 1, ownerName, "savings");
            return accounts.add(account);
        } else if (accountType.equals("checking")) {
            var account = new CheckingAccount(accounts.size() + 1, ownerName, "checking");
            return accounts.add(account);
        }

        return false;
    }

    public static boolean deposit(long accountNumber, double amount) {
        BankAccount account = existAccount(accountNumber);

        if (amount < 0) {
            return false;
        }

        if (account != null) {
            return account.deposit(amount);
        }

        return false;
    }

    public static double withdraw(long accountNumber, double amount) {
        BankAccount account = existAccount(accountNumber);

        if (amount < 0) {
            return -1;
        }

        if (account != null) {
            return account.withdraw(amount);
        }

        return -1;
    }

    public static boolean transfer(long accountNumber, long targetAccountNumber, double amount) {
        BankAccount account = existAccount(accountNumber);
        BankAccount targetAccount = existAccount(targetAccountNumber);

        if (amount < 0) {
            return false;
        }

        if (account != null && targetAccount != null) {
            account.balance -= amount;
            targetAccount.balance += amount;
            return true;
        }

        return false;
    }

    public static boolean applyInterestRate(long accountNumber) {
        BankAccount account = existAccount(accountNumber);

        if (account == null || !account.accountType.equals("savings")) {
            return false;
        }

        SavingsAccount savAcc = (SavingsAccount) existAccount(accountNumber);

        if (savAcc != null) {
            return savAcc.applyInterest(1);
        }

        return false;
    }

    public static boolean changeOverdraftLimit(long accountNumber, double overdraftLimit) {
        BankAccount account = existAccount(accountNumber);

        if (account == null || !account.accountType.equals("checking")) {
            return false;
        }

        CheckingAccount ckAcc = (CheckingAccount) existAccount(accountNumber);

        if (ckAcc != null) {
            return ckAcc.setOverdraftLimit(overdraftLimit);
        }

        return false;
    }

    public static boolean displayAccountInfo(long accountNumber) {
        BankAccount account = existAccount(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.accountType.equals("savings")) {
            SavingsAccount savAcc = (SavingsAccount) existAccount(accountNumber);
            System.out.println("Account number: " + savAcc.accountNumber +
                    ", Owner name: " + savAcc.ownerName +
                    ", Balance: " + savAcc.balance +
                    ", Interest rate: " + savAcc.getInterestRate());
        } else if (account.accountType.equals("checking")) {
            CheckingAccount ckAcc = (CheckingAccount) existAccount(accountNumber);
            System.out.println("Account number: " + ckAcc.accountNumber +
                    ", Owner name: " + ckAcc.ownerName +
                    ", Balance: " + ckAcc.balance +
                    ", Overdraft limit: " + ckAcc.getOverdraftLimit());
        }

        return false;
    }

    private static BankAccount existAccount(long accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                return accounts.get(i);
            }
        }

        return null;
    }
}