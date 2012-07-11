package com.shivank.billingsystem.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.shivank.billingsystem.test.bill.BillManagerTests;
import com.shivank.billingsystem.test.discount.DiscountManagerTests;
import com.shivank.billingsystem.test.item.ItemManagerTests;
import com.shivank.billingsystem.test.user.UserManagerTests;

/**
 * Root level test class which runs all the tests for Billing System 
 */
public class AllTests {

	public static Test suite(){
		
		TestSuite suite  = new TestSuite();
		suite.addTestSuite(BillManagerTests.class);
		suite.addTestSuite(UserManagerTests.class);
		suite.addTestSuite(ItemManagerTests.class);
		suite.addTestSuite(DiscountManagerTests.class);
		suite.addTestSuite(SystemManagerTest.class);
		
		
		return suite;
	}
}
