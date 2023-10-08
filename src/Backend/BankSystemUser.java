package Backend;

import java.util.ArrayList;

public class BankSystemUser extends BankSystem {
	private int userId;
	private String username;
	private String pasword;

	public BankSystemUser(String username, String password) {
//		super.updateUsers();
		this.username = username;
		this.pasword = password;
		defineUserId(username);
	}
	
	private void defineUserId(String username) {
		userId = super.findIdByUsername(username);
	}
	

}
