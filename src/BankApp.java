import java.util.Scanner;

public final class BankApp {
    public static void showMenu() {
        boolean running = true;

        while (running) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("==============================");
            System.out.println("      WELCOME TO MY BANK      ");
            System.out.println("==============================\n");

            System.out.println("Please choose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Apply Interest (Savings Accounts)");
            System.out.println("6. Change Overdraft Limit (Checking Accounts)");
            System.out.println("7. Display Account Info");
            System.out.println("8. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("------------------------------");
                    System.out.println("Choose account type:");
                    System.out.println("1. Savings Account");
                    System.out.println("2. Checking Account");
                    System.out.println("------------------------------");
                    System.out.print("Enter your choice: ");

                    String accountType = scanner.nextLine();
                    String ownerName;

                    switch (accountType) {
                        case "1":
                            System.out.print("Enter owner name: ");
                            ownerName = scanner.nextLine();

                            boolean savingsRes = Bank.createAccount(ownerName, "savings");
                            if (savingsRes) {
                                System.out.println("Account for " + ownerName + " successfully created.");
                            } else {
                                System.out.println("Account cannot be created.");
                            }

                            break;
                        case "2":
                            System.out.print("Enter owner name: ");
                            ownerName = scanner.nextLine();

                            boolean checkingRes = Bank.createAccount(ownerName, "checking");
                            if (checkingRes) {
                                System.out.println("Account for " + ownerName + " successfully created.");
                            } else {
                                System.out.println("Account cannot be created.");
                            }

                            break;
                        default:
                            System.out.println("Operation not allowed for this account type.");
                    }
                    break;
                case "2":
                    String accountNumber;
                    String amount;

                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    amount = scanner.nextLine();

                    if (isNumber(accountNumber) && isNumber(amount)) {
                        boolean depRes = Bank.deposit(Long.parseLong(accountNumber), Double.parseDouble(amount));

                        if (depRes) {
                            System.out.println("Deposit successfully completed.");
                        } else {
                            System.out.println("Deposit cannot be completed.");
                        }

                        break;
                    }

                    System.out.println("Incorrect account number or amount.");
                    break;
                case "3":
                    String accountNumber1;
                    String amount1;

                    System.out.print("Enter account number: ");
                    accountNumber1 = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    amount1 = scanner.nextLine();

                    if (isNumber(accountNumber1) && isNumber(amount1)) {
                        double withRes = Bank.withdraw(Long.parseLong(accountNumber1), Double.parseDouble(amount1));

                        if (withRes != -1) {
                            System.out.println("Withdrawal " + withRes + " successfully completed.");
                        } else {
                            System.out.println("Withdrawal cannot be completed.");
                        }

                        break;
                    }

                    System.out.println("Incorrect account number or amount.");
                    break;
                case "4":
                    String sourceAccountNumber;
                    String targetAccountNumber;
                    String transferAmount;

                    System.out.print("Enter source account number: ");
                    sourceAccountNumber = scanner.nextLine();

                    System.out.print("Enter target account number: ");
                    targetAccountNumber = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    transferAmount = scanner.nextLine();

                    if (isNumber(sourceAccountNumber) && isNumber(targetAccountNumber) && isNumber(transferAmount)) {
                        boolean trRes = Bank.transfer(Long.parseLong(sourceAccountNumber),
                                Long.parseLong(targetAccountNumber), Double.parseDouble(transferAmount));

                        if (trRes) {
                            System.out.println("Transfer from №" + sourceAccountNumber + " to №" + targetAccountNumber + " successfully completed.");
                        } else {
                            System.out.println("Transfer cannot be completed.");
                        }

                        break;
                    }

                    System.out.println("Incorrect account number, target account number or amount.");
                    break;
                case "5":
                    String accountNumber2;
                    System.out.print("Enter account number: ");
                    accountNumber2 = scanner.nextLine();

                    if (isNumber(accountNumber2)) {
                        boolean rateRes = Bank.applyInterestRate(Long.parseLong(accountNumber2));

                        if (rateRes) {
                            System.out.println("Interest rate successfully applied.");
                        } else {
                            System.out.println("Interest cannot be applied.");
                        }
                    }

                    break;
                case "6":
                    String accountNumber3;
                    String limit;

                    System.out.print("Enter account number: ");
                    accountNumber3 = scanner.nextLine();

                    System.out.print("Enter overdraft limit: ");
                    limit = scanner.nextLine();

                    if (isNumber(accountNumber3)) {
                        boolean ovdrRes = Bank.changeOverdraftLimit(Long.parseLong(accountNumber3), Double.parseDouble(limit));

                        if (ovdrRes) {
                            System.out.println("Overdraft limit successfully changed.");
                        } else {
                            System.out.println("Overdraft limit cannot be changed.");
                        }
                    }

                    break;
                case "7":
                    String accountNumber4;
                    System.out.print("Enter account number: ");
                    accountNumber4 = scanner.nextLine();

                    if (isNumber(accountNumber4)) {
                        Bank.displayAccountInfo(Long.parseLong(accountNumber4));
                    }

                    String choice2;
                    System.out.println("Enter N to continue or X to exit.");
                    choice2 = scanner.nextLine();

                    if (choice2.equals("X")) {
                        running = false;
                    }

                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("Incorrect option.");
            }
            delay();
        }
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
