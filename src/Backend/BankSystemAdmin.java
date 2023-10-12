package Backend;

public class BankSystemAdmin extends BankSystem{
	private int userId;
	private String username;
	private String password;
	
	public BankSystemAdmin(int userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		checkingLogin();
	}
	
	public void checkingLogin() {
		if (!loginAdmin()) {
	        throw new IllegalStateException("Admin not logged in as CLIENT");
	    }
		
	}
	
	
	public boolean loginAdmin() {
		if(super.login(userId,username,password) == true) {
			if(super.getAccountType(username,password).equals("ADMIN")) {
				return true;
			}
		}
		return false;
	}
	
	public void changePassword(int userId, String newPassword) {
		if (!loginAdmin()) {
	        throw new IllegalStateException("User not logged in as CLIENT");
	    }
		for (User now : getUsers()) {
			if (now.getUserId() == userId) {
				now.setPassword(newPassword);
				break;
			}
		}
		updateFile();
	}

	public void changeAccountValue(int userId, int newAccountValue) {
		if (!loginAdmin()) {
	        throw new IllegalStateException("User not logged in as CLIENT");
	    }
		for (User now : getUsers()) {
			if (now.getUserId() == userId) {
				now.setAccountValue(newAccountValue);
				break;
			}
		}
		updateFile();
	}

	public String changeAccountId(int userId, int newUserId) {
		if (!loginAdmin()) {
	        throw new IllegalStateException("User not logged in as CLIENT");
	    }
		int res = -1;
		for (User now : getUsers()) {
			if (now.getUserId() == userId) {
				now.setUserId(newUserId);
				res = now.getUserId();
				break;
			}
		}
		assert (res == newUserId);
		String output = "userId:" + userId + "->" + res;
		updateFile();
		return output;
	}

	protected String changeUsername(int userId, String newUsername) {
		if (!loginAdmin()) {
	        throw new IllegalStateException("User not logged in as CLIENT");
	    }
		String res = "";
		String lastUsername = "";
		for (User now : getUsers()) {
			if (now.getUserId() == userId) {
				lastUsername = now.getUsername();
				now.setUsername(newUsername);
				res = now.getUsername();
				break;
			}
		}
		assert (res == newUsername);
		String output = "username:" + lastUsername + "->" + newUsername;
		updateFile();
		return output;
	}
	protected int getBalance() {
		if (!loginAdmin()) {
	        throw new IllegalStateException("User not logged in as CLIENT");
	    }
		
		for (User now : super.getUsers()) {
			if (now.getUserId() == userId) {
				return now.getAccountValue();
			}
		}
		return -1;
	}
	
	protected String getAllUsers() {
		String out="";
		for(User now: getUsers()) {
			out+=now.toString();
		}
		return out;
	}
	
	@Override
	public String toString() {
		assert(loginAdmin());
		String result="You're logged as " + username+", userId:"+userId+", accountValue:"+ getBalance()+", accountType:" + getAccountType(username,password);
		return result;
	}
	

}
