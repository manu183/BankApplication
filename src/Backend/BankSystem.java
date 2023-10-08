package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem{
	private ArrayList<User> users;

	public BankSystem() {
		super();
		this.users = new ArrayList<User>();
		updateUsers();
	}
	//This method will update ArrayList according to .txt file
	protected void updateUsers() {
		File file = new File("Files/users.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				int id = scanner.nextInt();
				String username = scanner.next();
				String password = scanner.next();
				int accountValue = scanner.nextInt();
				String accountType = scanner.next();
				User atual = new User(id,username,password,accountValue, accountType);
				System.out.println(atual.toString());
				users.add(atual);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This method will check if username and password are correct
	public boolean logIn(String username, String password) {
		for(User now: users) {
			if(now.getUsername().equals(username) && now.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	
	//This method will return the account type
	public String getAccountType(String username, String password) {
		User user = findUserById(findIdByUsername(username));
		return user.getAccountType();
	}
	
	//This method will update .txt file according to users ArrayList
	protected void updateFile() {
		File file = new File("Files/users.txt");
		
		
		try (PrintWriter writer = new PrintWriter(file)) {
			for(User atual: users) {
				String line = atual.getUserId() + " " + atual.getUsername() +" "+ atual.getPassword()+" "+atual.getAccountValue()+ " " +atual.getAccountType();  
				writer.println(line);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Get users
	private ArrayList<User> getUsers() {
		return users;
	}
	
	//Set users
	private void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	//ALL BANK METHODS
	
	//This method will find an User by it's username
	protected User findUserById(int userId) {
		User result = new User(-1, "notFound", "notFound", -1,"notFound");
		for(User atual: users) {
			if(atual.getUserId()==userId) {
				result=atual;
				break;
			}
		}
		return result;
	}
	
	//Find userId by Username
	protected int findIdByUsername(String username) {
		int result = -1;
		for(User now: users) {
			if(now.getUsername().equals(username)) {
				result=now.getUserId();
				break;
			}
		}
		return result;
	}
	
	protected void makeDeposit(int userId,int value) {
		assert(value>0);
		for(User now: users) {
			if(now.getUserId()==userId) {
				now.setAccountValue(now.getAccountValue()+value);
				break;
			}
		}
	}
	
	protected void makeTransfer(int userId1, int userId2, int value) {
		assert(value>0);
		for(User now:users) {
			if(now.getUserId()==userId1)
				now.setAccountValue(now.getAccountValue()-value);
			if(now.getUserId()==userId2)
				now.setAccountValue(now.getAccountValue()+value);
		}
	}
	



	

}
