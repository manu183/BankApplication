package Backend;

public class Test {
	public static void main(String[] args) {
		
		System.out.println("TESTS OF BANK_SYSTEM_USER");
		String username = "Joao";
		String password = "password";
		BankSystemUser user = new BankSystemUser(username,password);
		System.out.println(user.loginUser());
		System.out.println(user.toString());
		user.makeTransfer(2,30);
		System.out.println(user.toString());
		
		System.out.println("TESTS OF BANK_SYSTEM_ADMIN");
		String username1="Joaquina";
		String password1="password";
		int userId=7;
		BankSystemAdmin admin = new BankSystemAdmin(userId, username1, password1);
		admin.changeAccountValue(1, 15);
		System.out.println(admin.changeAccountId(1, 2));
		System.out.println(admin.getAllUsers());
		System.out.println(admin.changeUsername(2, "Mariana"));
		
	}
}
