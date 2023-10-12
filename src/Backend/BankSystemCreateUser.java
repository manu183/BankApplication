package Backend;

import java.util.Random;

public class BankSystemCreateUser extends BankSystem {
	private String username;
	private String password;
	
	public BankSystemCreateUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		createAccount();
	}
	
	private void createAccount() {
		int generatedUserId = generateUserId();
		User newUser = new User(generatedUserId, username, password, 0, "CLIENT");
		super.addNewUser(newUser);
		
		System.out.println("Your user was created:{"
				+ "\nusername:"+username
				+ "\npassword:"+password
				+ "\naccountId:" + generatedUserId
				+"\n}");
	}
	
	private int generateUserId() {
		Random rand = new Random();
		int n = rand.nextInt(50);
		n++;
		while(super.existsUser(n)) {
			n = rand.nextInt(1000);
			n++;
			if(!super.existsUser(n)) {
				return n;
			}
		}
		return n;	
	}

}
