package InTerminal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Backend.BankSystem;
import Backend.BankSystemAdmin;
import Backend.BankSystemCreateUser;
import Backend.BankSystemUser;

public class BankApplication {
	static final String parentesis = "*********";
	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	static final int maxTries = 5;

	public static void closeApp() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 9; i++) {
			System.out.println(parentesis.repeat(3));
		}
		System.out.println("Goodbye. See you soon!");
		LocalDateTime date = LocalDateTime.now();
		String formatedDate = date.format(formatter);
		System.out.println("Date: " + formatedDate);
		System.exit(1);
	}

	public static void sleepNSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void sleepSeconds() {
		sleepNSeconds(1);
	}

	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.now();
		String formatedDate = date.format(formatter);
		System.out.println("Bank Application");
		System.out.println("Date: " + formatedDate);

		while (true) {

			System.out.println(
					"Options: \n 1.Login your account \n 2.Create an account \n 3.Admin Painel \n 4.Exit bank application");
			System.out.print("\nOption selected:");
			Scanner keyboard = new Scanner(System.in);
			int selectedOption = keyboard.nextInt();
//		keyboard.close();

			switch (selectedOption) {
			case 1:
				System.out.println();
				System.out.println(parentesis + "Login as User" + parentesis);

				String username = "";
				String password = "";
				BankSystemUser user = null;
				boolean isLogged = false;
				int times = 0;// If login is not accepted it will indicate in the next loop "Wrong password"
				while (!isLogged) {
					if (times >= 1) {
						// System.err.println("\nWrong password! Try again");
						if (times >= maxTries) {
							System.err.println("To many tries!!! Closing application...");
							keyboard.close();
							closeApp();
						}
					}
					System.out.print("Enter your username:");
					username = keyboard.next();
					System.out.print("Enter your password:");
					password = keyboard.next();
					try {
						user = new BankSystemUser(username, password);
						isLogged = user.loginUser();
						if (isLogged) {
							break; // Exit loop because user logged in
						}
					} catch (IllegalStateException e) {
						// Handle the exception, log it, or perform any necessary cleanup
						System.err.println("\nInvalid login credentials. Please try again.");
					}
					times++;

					// Time between two tries to login. 200ms
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// There is no need to check if user is an CLIENT because object will be accept
				// if it's not
//					System.out.println("You are logged in " + clientSession.getUsername());
				System.out.println(
						"\nOptions: \n 1.Get account balance \n 2.Make a deposit \n 3.Make a transfer to other account \n 4.Delete account");
				System.out.print("Option selected:");
				selectedOption = keyboard.nextInt();
				System.out.println();
				switch (selectedOption) {
				case 1:
					System.out.println(parentesis + "Get balance" + parentesis);
					System.out.println("Balance of " + user.getUsername() + ":" + user.getBalance());
					sleepSeconds();
					break;

				case 2:
					System.out.println(parentesis + "Make a deposit" + parentesis);
					System.out.print("Value of deposit:");
					int deposit = keyboard.nextInt();
					user.makeDeposit(deposit);
					System.out.println("Now your balance is:" + user.getBalance());
					sleepSeconds();
					break;
				case 3:
					System.out.println(parentesis + "Make a transfer" + parentesis);
					System.out.print("Value of transfer:");
					int transferValue = keyboard.nextInt();
					System.out.print("Id of destionation account:");
					int accountDestination = keyboard.nextInt();
					System.out.print(
							"Are you sure you want to transfer " + transferValue + " to " + accountDestination + "?");
					String verify = keyboard.next();
					String inputs[] = { "Yes", "YES", "yes", "y", "Y" };
					for (String now : inputs) {
						if (now.equals(verify)) {
							int valueBefore = user.getBalance();
							try {
								user.makeTransfer(accountDestination, transferValue);
							} catch (IllegalStateException e) {
								System.err.println(e.getMessage());
							}
							if (user.getBalance() == valueBefore - transferValue) {
								System.out.println("Transfer made with success!");
							} else {
								System.err.println("An error ocurred. The transfer was not made!");
							}

							break;
						}
					}
					sleepSeconds();
					break;
				case 4:
					System.out.println(parentesis + "Delete my account" + parentesis);
					System.out.println("Deleting you account is a permanent operation!");
					System.out.print("Are you sure you want to delete your account?");
					String decision = keyboard.next();
					String options[] = { "Yes", "YES", "yes", "y", "Y" };

					for (String now : options) {
						if (now.equals(decision)) {
							System.out.print("Input again you password:");
							password = keyboard.next();
							try {
								user.deleteAccount(password);
							} catch (IllegalStateException e) {
								System.err.println(e.getMessage());
							}
							break;
						}
					}
					break;

				}
				sleepSeconds();
				break;

			case 2:
				System.out.println(parentesis + "Create an account" + parentesis);
				System.out.print("Enter the username of your new account:");
				String account_username = keyboard.next();
				System.out.print("Enter the password of your new account:");
				String account_password = keyboard.next();
				BankSystemCreateUser newUser = new BankSystemCreateUser(account_username, account_password);
				sleepSeconds();
				break;

			case 3:
				System.out.println(parentesis + "Admin painel" + parentesis);
				String admin_username = "";
				int admin_id = -1;
				String admin_password = "";
				BankSystemAdmin admin = null;
				isLogged = false;
				times = 0;// If login is not accepted it will indicate in the next loop "Wrong password"
				// maxTries is already defined in case 1.
				while (!isLogged) {
					if (times >= 1) {
						// System.err.println("\nWrong password! Try again");
						if (times >= maxTries) {
							System.err.println("To many tries!!! Closing application...");
							keyboard.close();
							closeApp();
						}
					}
					System.out.print("Enter your username:");
					admin_username = keyboard.next();
					System.out.print("Enter your id:");
					admin_id = keyboard.nextInt();
					System.out.print("Enter your password:");
					admin_password = keyboard.next();
					try {
						admin = new BankSystemAdmin(admin_id, admin_username, admin_password);
						isLogged = admin.loginAdmin();
						if (isLogged) {
							break; // Exit loop because user logged in
						}
					} catch (IllegalStateException e) {
						// Handle the exception, log it, or perform any necessary cleanup
						System.err.println("\nInvalid login credentials. Please try again.");
					}
					times++;

					// Time between two tries to login. 200ms
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// There is no need to check if user is an CLIENT because object will be accept
				// if it's not
//					System.out.println("You are logged in " + clientSession.getUsername());
				System.out.println(
						"\nOptions: \n 1.Change user password \n 2.Change account value \n 3.Change account id");
				System.out.print("Option selected:");
				selectedOption = keyboard.nextInt();
				switch (selectedOption) {
				case 1:
					System.out.println(parentesis + "Change user password" + parentesis);
					System.out.print("User id:");
					int user_id = keyboard.nextInt();
					System.out.print("Set new password:");
					String new_password = keyboard.next();
					admin.changePassword(user_id, new_password);
					sleepSeconds();
					break;

				case 2:
					System.out.println(parentesis + "Change account value" + parentesis);
					System.out.print("User id:");
					user_id = keyboard.nextInt();
					System.out.print("New account value:");
					int new_account_value = keyboard.nextInt();
					admin.changeAccountValue(user_id, new_account_value);
					sleepSeconds();
					break;
				case 3:
					System.out.println(parentesis + "Change user id" + parentesis);

					System.out.print("User id:");
					user_id = keyboard.nextInt();
					System.out.print("New user id:");
					int new_user_id = keyboard.nextInt();
					admin.changeAccountId(user_id, new_user_id);
					sleepSeconds();
					break;
				}
				break;
			case 4:
				System.out.println(parentesis + "Exit application" + parentesis);
			default:
				System.err.println("No option were selected. Exiting bank application...");
				keyboard.close();
				closeApp();
			}
			System.out.println("\n\n\n");
		}

	}

}
