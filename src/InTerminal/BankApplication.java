package InTerminal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Backend.BankSystem;
import Backend.BankSystemUser;

public class BankApplication {
	static final String parentesis = "*********";
	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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

	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.now();
		String formatedDate = date.format(formatter);
		System.out.println("Bank Application");
		System.out.println("Date: " + formatedDate);

		while (true) {

			System.out.println(
					"Options: \n 1.Login your account \n 2.Create an account \n 3.Delete my account \n 4.Admin Painel \n 5.Exit bank application");
			System.out.print("Option selected:");
			Scanner keyboard = new Scanner(System.in);
			int selectedOption = keyboard.nextInt();
//		keyboard.close();

			switch (selectedOption) {
			case 1:
				System.out.println(parentesis + "Login" + parentesis);

				String username = "";
				String password = "";
				BankSystem sessionTemp = new BankSystem();
				boolean isLogged = false;
				int times = 0;// If login is not accepted it will indicate in the next loop "Wrong password"
				final int maxTries = 5;
				while (!isLogged) {
					if (times >= 1) {
						System.err.println("\nWrong password! Try again");
						if (times >= maxTries) {
							System.err.println("To many tries!!! Closing application...");
							keyboard.close();
							closeApp();
						}
					}
					System.out.print("\nEnter your username:");
					username = keyboard.next();
					System.out.print("Enter your password:");
					password = keyboard.next();


					isLogged = sessionTemp.logIn(username, password);
					times++;
				}
				//Check if the session type is an ADMIN. If it is an ADMIN the access to this section is denied.
				if(sessionTemp.getAccountType(username,password).equals("ADMIN"))
					System.out.println("You are an ADMIN. Please choose option number 4.");
				else {
					//Create an object BankSystemUser
					BankSystemUser clientSession = new BankSystemUser(username,password);
//					System.out.println("You are logged in " + clientSession.getUsername());
					System.out.println("Options: \n 1.Get account balance \n 2.Make a deposit \n 3.Make a transfer to other account");
					selectedOption = keyboard.nextInt();
					switch (selectedOption) {
					case 1:
						System.out.println(parentesis+"Get balance"+parentesis);
//						System.out.println("Balance of "+ clientSession.getUsername()+":"+clientSession.getBalance());
						break;
						
					case 2:
						System.out.println(parentesis+"Make a deposit"+parentesis);
						System.out.print("Value of deposit:");
						int deposit = keyboard.nextInt();
//						System.out.println("Now your balance is:" + clientSession.getBalance());
						break;
					}
				}
				break;
				
			case 2:
				System.out.println(parentesis + "Create an account" + parentesis);
				break;
			
			case 3:
				System.out.println(parentesis + "Delete my account" + parentesis);
				break;
			case 4:
				System.out.println(parentesis + "Admin paisnel" + parentesis);
				break;
			case 5:
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
