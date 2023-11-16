package accounts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

	Account account;
	
	@BeforeEach
	void setUp() {
		account = new Account();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void username() {
		//correct ending
		account.username("gcr170000@utdallas.edu");
		assertEquals(true, account.getUsername());
		//incorrect ending 
		account.username("gcr170000@gmail.com");
		assertEquals(true, account.getUsername());
	}
	
	@Test
	void initPwd() {
		//correct
		account.initPwd("abC123de");
		assertEquals(true, account.getPassword());
		//incorrect- too short, not enough chars, no uppercase, no #
		account.initPwd("abcde");
		assertEquals(false, account.getPassword());
	}
	
	@Test
	void retypePwd() {
		//match
		account.retypePwd("abC123de");
		assertEquals(true, account.getPassword());
		//no match
		account.retypePwd("Abc123de");
		assertEquals(false, account.getPassword());
	}
	
	@Test
	void login() {
		account.login("gcr170000@utdallas.edu", "abC123de");
		assertEquals(true, account.getLogin());
		account.login("gcr170000@utdallas.edu", "Abc123de");
		assertEquals(false, account.getLogin());		
	}
	
}
