//import java.util.*;
//
//public class CreateAccount {
//		
//	static //scanner
//	Scanner input = new Scanner(System.in);
//	private static String email;
//	private static String initialPwd;
//	private static String retypePwd;
//	private static String emailError = "“Username must be UTD email (ending in @utdallas.edu)";
//	private static String initialError = "Password must be at least 8 characters long with at least one of each: letter, number, uppercase letter, & special character";
//	private static String retypeError = "Passwords must match";
//	private static String validMessage = "Registration is successful! Welcome to the UTD Gym Tracker";
//	
//	public CreateAccount() {
//		email = "";
//		initialPwd = "";
//	}
//	
//	public static boolean isValidEmail(String email) {
//		
//		String address = "@utdallas.edu";
//		
//		if (email.endsWith(address))
//			return true;
//		else
//			return false;
//	}
//	
//	public static boolean isValidInitial(String initialPwd) {
//		
//		//password must contain letters, and at least 1 number and uppercase
//		String numRegex = ".*[0-9].*";
//		String alphaLowRegex = ".*[a-z].*";
//		String alphaUppRegex = ".*[A-Z].*";
//		
//		if (initialPwd.matches(numRegex) && initialPwd.matches(alphaLowRegex) && initialPwd.matches(alphaUppRegex) && initialPwd.length() > 7 && initialPwd.length() < 13)
//			return true;
//		else
//			return false;
//	}
//	
//	
//	public static boolean isValidRetype(String initialPwd, String retypePwd) {
//		
//		if (initialPwd.equals(retypePwd))
//			return true;
//		else
//			return false;
//		
//	}
//	
//	public static void main(String[] args) {
//		
//		System.out.println("Welcome");
//		//Login GUI and Create Account option
//		//if they select Create Account, proceed below
//		
//		//check email format
//		System.out.println("Enter your UTD email: ");
//		email = input.nextLine();
//		while(!isValidEmail(email)) {
//			System.out.println(emailError);
//			System.out.println("Enter your UTD email: ");
//			email = input.nextLine();
//		}
//			
//		
//		//check initial password
//		System.out.println("Enter a password: ");
//		initialPwd = input.nextLine();
//		while(!isValidInitial(initialPwd)) {
//				System.out.println(initialError);
//				System.out.println("Enter a password: ");
//				initialPwd = input.nextLine();
//		}
//		
//		//check retyped password
//		System.out.println("Re-enter password: ");
//		retypePwd = input.nextLine();
//		if(!isValidRetype(initialPwd, retypePwd)) {
//			System.out.println(retypeError);
//			System.out.println("Re-enter password: ");
//			retypePwd = input.nextLine();
//		}
//				
//		
//		//if u followed directions 
//		System.out.println(validMessage);
//
//	}
//
//}
