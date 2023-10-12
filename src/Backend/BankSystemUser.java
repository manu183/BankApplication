package Backend;

import java.util.ArrayList;

public class BankSystemUser extends BankSystem {
	private int userId;
	private String username;
	private String password;

	public BankSystemUser(String username, String password) {
//		super.updateUsers();
		this.username = username;
		this.password = password;
		this.userId = findIdByUsername(username);
		checkingLogin();
	}

	public void checkingLogin() {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}

	}

	public boolean loginUser() {
		if (super.login(username, password) == true) {
			if (super.getAccountType(username, password).equals("CLIENT")) {
				return true;
			}
		}
		return false;
	}

	// USER METHODS
	public void makeDeposit(int value) {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}
		assert (value > 0);
		for (User now : super.getUsers()) {
			if (now.getUserId() == userId) {
				now.setAccountValue(now.getAccountValue() + value);
				break;
			}
		}
		updateFile();
	}

	//TEMPORARY IMPLEMENTATION
	//This method is implemented with O(n^2)
	//TODO Implement a better version of this method
	public void makeTransfer(int userId2, int value) {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}
		if (userId2 == userId) {
			throw new IllegalStateException("You cannot make a transfer to yourself");
		}
		assert (value > 0);
		for (User now : super.getUsers()) {
			if (now.getUserId() == userId) {
				for(User now2: super.getUsers()) {
					if (now.getUserId() == userId2) {
						now.setAccountValue(now.getAccountValue() - value);
						now.setAccountValue(now.getAccountValue() + value);
					}
				}
			}
				
		}
		updateFile();
	}

	public int getBalance() {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}

		for (User now : super.getUsers()) {
			if (now.getUserId() == userId) {
				return now.getAccountValue();
			}
		}
		return -1;
	}
	public String getUsername() {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}

		for (User now : super.getUsers()) {
			if (now.getUserId() == userId) {
				return now.getUsername();
			}
		}
		return "ERROR!!!USER NOT FOUND";
	}
	
	public String deleteAccount(String password) {
		if (!loginUser() || !password.equals(this.password)) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}
		super.removeUser(userId, username, password);
		
		String res = "Id:" + userId +", username:" + username +"was deleted with success!"; 
		return res;
	}

	@Override
	public String toString() {
		if (!loginUser()) {
			throw new IllegalStateException("User not logged in as CLIENT");
		}
		String result = "You're logged as " + username + ", userId:" + userId + ", accountValue:" + getBalance()
				+ ", accountType:" + getAccountType(username, password);
		return result;
	}

}
