package com.shivank.billingsystem.test.bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillItem;
import com.shivank.billingsystem.bill.BillManager;
import com.shivank.billingsystem.bill.BillNotFoundException;
import com.shivank.billingsystem.bill.InvalidBillException;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserManager;

/**
 * This class contains tests related to bill and bill manager
 */
public class BillManagerTests extends TestCase{
	
	private static BillManager manager = null; 
	private static UserManager userManager = null;

	static{
		try {
			manager = BillingSystem.getSystemManager().getBillManager();
			userManager = BillingSystem.getSystemManager().getUserManager();
			BillingSystem.usage();
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}

	
	public void testGetValidBill(){
		try {
			Bill bill = manager.getBill("00B1");
			assertNotNull(bill);
			assertEquals("00B1", bill.getBillId());
		} catch (BillNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	public void testVaildBillDetails(){
		try {
			Bill bill = manager.getBill("00B1");
			assertNotNull(bill);
			assertEquals("00B1", bill.getBillId());
			assertEquals("00U1", bill.getUserId());
			assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()), new SimpleDateFormat("dd/MM/yyyy").format(bill.getDate()));
			assertEquals(335f, bill.getGrossPrice());
			assertEquals(15f, bill.getDiscount());
			assertEquals(320f, bill.getNetPrice());
			
			assertEquals(2, bill.getBillItems().size());
			BillItem item1 = bill.getBillItems().get(0);
			assertEquals("00I1", item1.getItemId());
			assertEquals(5, item1.getQty());
			assertEquals(125f, item1.getGrossPrice());
			assertEquals(125f, item1.getNetPrice());
			assertEquals(0f, item1.getDiscount());
			
			BillItem item2 = bill.getBillItems().get(1);
			assertEquals("00I2", item2.getItemId());
			assertEquals(10, item2.getQty());
			assertEquals(300f, item2.getGrossPrice());
			assertEquals(210f, item2.getNetPrice());
			assertEquals(90f, item2.getDiscount());
			
		} catch (BillNotFoundException e) {
			fail(e.getMessage());
		}		
	}

	public void testGetInvalidBill(){
		try {
			manager.getBill("00B6");
			fail("Expected exception as provided bill number is invalid");
		} catch (BillNotFoundException e) {
			//Success Scenario
			e.getMessage();
		}
	}
	
	public void testCreateInvalidBill(){
		try {
			new Bill("00B10", "Invalid", Calendar.getInstance().getTime());
			fail("Expected InvalidBillException.");
		} catch (InvalidBillException e) {
			//Success
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}

	public void testCreateInvalidBillItem(){
		try {
			Bill bill = new Bill("00B1", "00U1", Calendar.getInstance().getTime());
			bill.addItem("Invalid", 10);
			fail("Expected ItemNotException.");
		} catch (InvalidBillException e) {
			fail(e.getMessage());
		} catch (ItemNotFoundException e) {
			//Success.
			e.getMessage();
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}
	
	public void testCreateValidBill1(){
		try {
			User user = userManager.getUser("00U1"); 
			List<BillItem> items = new ArrayList<BillItem>();
			items.add(new BillItem(user, "00I1", 5));
			items.add(new BillItem(user, "00I2", 10));
			items.add(new BillItem(user, "00I3", 15));
			Bill bill = new Bill("00B1", "00U1", Calendar.getInstance().getTime(), items);
			System.out.println(bill);
		} catch (InvalidBillException e) {
			fail(e.getMessage());
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}
	
	
}
