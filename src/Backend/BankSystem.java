package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class BankSystem {
	private ArrayList<User> users;

	public BankSystem() {
		super();
		this.users = new ArrayList<User>();
		updateUsers();
	}

	// This method will update ArrayList according to .txt file
	protected void updateUsers() {
		File file = new File("Files/users.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				int id = scanner.nextInt();
				String username = scanner.next();
				String password = scanner.next();
				int accountValue = scanner.nextInt();
				String accountType = scanner.next();
				User atual = new User(id, username, password, accountValue, accountType);
//				System.out.println(atual.toString());
				users.add(atual);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//LOGIN
	// This method will check if username and password are correct
	protected boolean login(String username, String password) {
		for (User now : users) {
			if (now.getUsername().equals(username) && now.getPassword().equals(password))
				return true;
		}
		return false;
	}
	protected boolean login(int userId,String username, String password) {
		for (User now : users) {
			if (now.getUserId()==userId&& now.getUsername().equals(username) && now.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	//GET ACCOUNT TYPE
	// This method will return the account type
	protected String getAccountType(String username, String password) {
		String res ="NOTFOUNDUSER";
		for(User now: users) {
			if(now.getUsername().equals(username) && now.getPassword().equals(password)) {
				res = now.getAccountType();
				break;
			}
		}
		return res;
	}

	// This method will update .txt file according to users ArrayList
	protected void updateFile() {
		File file = new File("Files/users.txt");

		try (PrintWriter writer = new PrintWriter(file)) {
			for (int i = 0; i < users.size(); i++) {
				User now = users.get(i);
				String line = now.getUserId() + " " + now.getUsername() + " " + now.getPassword() + " "
						+ now.getAccountValue() + " " + now.getAccountType();

				// Check if it's the last iteration to avoid adding an extra newline
				if (i < users.size() - 1) {
					writer.println(line);
				} else {
					writer.print(line);
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get users
	protected ArrayList<User> getUsers() {
		return users;
	}
	
	protected void removeUser(int userId, String username, String password) {
		User toRemove = null;
		Iterator<User> iterator = users.iterator();
	    while (iterator.hasNext()) {
	        User now = iterator.next();
	        if (now.getUserId() == userId && now.getUsername().equals(username) && now.getPassword().equals(password)) {
	            iterator.remove(); // Use iterator to remove the element safely
	        }
	    }
	    
		if(existsUser(toRemove)) {
			throw new IllegalStateException("Some ERROR ocurred. User was not deleted");
		}
		updateFile();
	}
	
	protected boolean existsUser(User user) {
		for(User now: users) {
			if(now.equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean existsUser(int userId) {
		for(User now: users) {
			if(now.getUserId()==userId) {
				return true;
			}
		}
		return false;
	}
	
	protected void addNewUser(User user) {
		users.add(user);
		updateFile();
	}

	// Set users
//	private void setUsers(ArrayList<User> users) {
//		this.users = users;
//	}

	// ALL BANK METHODS
	
	
	//DANGER OPERATION
	// This method will find an User by it's username
//	protected User findUserById(int userId) {
//		User result = new User(-1, "notFound", "notFound", -1, "notFound");
//		for (User atual : users) {
//			if (atual.getUserId() == userId) {
//				result = atual;
//				break;
//			}
//		}
//		return result;
//	}

	// Find userId by Username
	protected int findIdByUsername(String username) {
		int result = -1;
		for (User now : users) {
			if (now.getUsername().equals(username)) {
				result = now.getUserId();
				break;
			}
		}
		return result;
	}

//	// USER METHODS
//	protected void makeDeposit(int userId, int value) {
//		assert (value > 0);
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				now.setAccountValue(now.getAccountValue() + value);
//				break;
//			}
//		}
//		updateFile();
//	}
//
//	protected void makeTransfer(int userId1, int userId2, int value) {
//		assert (value > 0);
//		for (User now : users) {
//			if (now.getUserId() == userId1)
//				now.setAccountValue(now.getAccountValue() - value);
//			if (now.getUserId() == userId2)
//				now.setAccountValue(now.getAccountValue() + value);
//		}
//		updateFile();
//	}
//
//	protected int getBalance(int userId) {
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				return now.getAccountValue();
//			}
//		}
//		return -1;
//	}

	// ADMIN METHODS

//	protected void changePassword(int userId, String newPassword) {
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				now.setPassword(newPassword);
//				break;
//			}
//		}
//		updateFile();
//	}
//
//	protected void changeAccountValue(int userId, int newAccountValue) {
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				now.setAccountValue(newAccountValue);
//				break;
//			}
//		}
//		updateFile();
//	}
//
//	protected String changeAccountId(int userId, int newUserId) {
//		int res = -1;
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				now.setUserId(newUserId);
//				res = now.getUserId();
//				break;
//			}
//		}
//		assert (res == newUserId);
//		String output = "userId:" + userId + "->" + newUserId;
//		return output;
//	}
//
//	protected String changeUsername(int userId, String newUsername) {
//		String res = "";
//		String lastUsername = "";
//		for (User now : users) {
//			if (now.getUserId() == userId) {
//				lastUsername = now.getUsername();
//				now.setUsername(newUsername);
//				res = now.getUsername();
//				break;
//			}
//		}
//		assert (res == newUsername);
//		String output = "username:" + lastUsername + "->" + newUsername;
//		return output;
//	}

}
