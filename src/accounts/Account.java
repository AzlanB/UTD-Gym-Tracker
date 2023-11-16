package accounts;

public class Account {
	
	private String email;
	private String password;
	private static String emailError = "“Username must be UTD email (ending in @utdallas.edu)";
	private static String initialError = "Password must be at least 8 characters long with at least one of each: letter, number, uppercase letter, & special character";
	private static String retypeError = "Passwords must match";
	private static String validMessage = "Registration is successful! Welcome to the UTD Gym Tracker";
	public static String loginMessage = "Welcome!";
	
	//account constructor
	public Account() {
		email = "";
		password = "";
	}
	
	//user email 
	public boolean username(String usr) {
		String address = "@utdallas.edu";
		if (usr.endsWith(address)) {
			email = usr;
			return true;
		} else {
			System.out.println(emailError);
			return false;
		}
	}
	
	public String getUsername() {
		return email;
	}
	
	//initially typed password
	public boolean initPwd(String pwd) {
		
		//password must contain letters, and at least 1 number and uppercase
		String numRegex = ".*[0-9].*";
		String alphaLowRegex = ".*[a-z].*";
		String alphaUppRegex = ".*[A-Z].*";
		
		if (pwd.matches(numRegex) && pwd.matches(alphaLowRegex) && pwd.matches(alphaUppRegex) && pwd.length() > 7 && pwd.length() < 13) {
			password = pwd;
			return true;
		} else {
			System.out.println(initialError);
			return false;
		}
	}
	
	//retype to confirm password 
	public boolean retypePwd(String pwd) {
		if(pwd.equals(password)) {
			System.out.println(validMessage);
			return true;
		} else {
			System.out.println(retypeError);
			return false;
		}
	}
	
	public String getPassword() {
		return password;
	}
	
	
	//allow user to login
	//after account has been created
	public boolean login(String usr, String pwd) {
		
		//both password and user must match, both are case sensitive
		if (usr.equals(email) || pwd.equals(password))
			return true;
		else
			return false;
	}
	
	public String getLogin() {
		return loginMessage;
	}
	
}
