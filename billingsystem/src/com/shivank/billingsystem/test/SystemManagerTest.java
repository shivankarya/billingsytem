package com.shivank.billingsystem.test;

import junit.framework.TestCase;

import com.shivank.billingsystem.ISystemManager;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.SeededSystemManager;
import com.shivank.billingsystem.XMLSystemManager;

/**
 * This class contains test related to System Manager.
 */
public class SystemManagerTest extends TestCase {

	public void testValidXMLSystemManager(){
		ISystemManager manager = new XMLSystemManager("data");
		try {
			manager.getBillManager();
			manager.getUserManager();
			manager.getItemManager();
			manager.getDiscountManager();			
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}

	public void testInvalidXMLSystemManager(){
		ISystemManager manager = new XMLSystemManager("invalid");
		try {
			manager.getBillManager();
			fail("Exception should have been thrown");
		} catch (ManagerException e) {
			// success.
		}
		try {
			manager.getUserManager();
			fail("Exception should have been thrown");
		} catch (ManagerException e) {
			// success.
		}
		try {
			manager.getItemManager();
			fail("Exception should have been thrown");
		} catch (ManagerException e) {
			// success.
		}
		try {
			manager.getDiscountManager();
			fail("Exception should have been thrown");
		} catch (ManagerException e) {
			// success.
		}
	}
	
	public void testValidSeededSystemManager(){
		ISystemManager manager = new SeededSystemManager();
		try {
			manager.getBillManager();
			manager.getUserManager();
			manager.getItemManager();
			manager.getDiscountManager();			
		} catch (ManagerException e) {
			fail(e.getMessage());
		}		
	}
	
}
