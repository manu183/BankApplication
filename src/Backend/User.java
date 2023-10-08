package Backend;

public class User {
	private int userId;
	private String username;
	private String password;
	private int accountValue;
	private String accountType;
	
	public User(int userId,String username, String password, int accountValue,String accountType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.accountValue = accountValue;
		this.accountType = accountType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(int accountValue) {
		this.accountValue = accountValue;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	@Override
	public String toString() {
		String result= "id:"+getUserId()+" username:" +getUsername()+" password:"+getPassword() + " value:"+getAccountValue() + "account Type:" + getAccountType();
//		String result= "id:"+userId+" username:" +username+" password:"+password + " value:"+accountValue + " account Type:" + accountType;

		return result;
	
	}
	
}
