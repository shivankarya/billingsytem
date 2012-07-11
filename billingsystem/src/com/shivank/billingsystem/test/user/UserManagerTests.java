package com.shivank.billingsystem.test.user;

import java.text.SimpleDateFormat;

import junit.framework.TestCase;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserManager;
import com.shivank.billingsystem.user.UserNotFoundException;
import com.shivank.billingsystem.user.UserType;

/**
 * This class contains tests related to users.
 */
public class UserManagerTests extends TestCase{

	private static UserManager manager = null;
	
	static{
		try {
			manager = BillingSystem.getSystemManager().getUserManager();
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}

	
	public void testGetValidUser(){
		try {
			User user = manager.getUser("00U1");
			assertNotNull(user);
			assertEquals("00U1", user.getId());
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	public void testVaildUserDetails(){
		try {
			User user = manager.getUser("00U1");
			assertNotNull(user);
			assertEquals("00U1", user.getId());
			assertEquals("User1", user.getName());
			assertEquals("12/12/2006", new SimpleDateFormat("dd/MM/yyyy").format(user.getDoj()));
			assertEquals(UserType.EMPLOYEE, user.getType());			
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}		
	}

	public void testGetInvalidUser(){
		try {
			manager.getUser("Invalid");
			fail("Expected exception as provided User number is invalid");
		} catch (UserNotFoundException e) {
			//Success Scenario
		}
	}	
}
