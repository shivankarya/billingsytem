package com.shivank.billingsystem.test.discount;

import junit.framework.TestCase;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillManager;
import com.shivank.billingsystem.bill.BillNotFoundException;
import com.shivank.billingsystem.discount.BillDiscount;
import com.shivank.billingsystem.discount.DiscountManager;
import com.shivank.billingsystem.discount.ItemDiscount;
import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserManager;
import com.shivank.billingsystem.user.UserNotFoundException;
import com.shivank.billingsystem.user.UserType;

/**
 * This class contains tests related to discounts.
 */
public class DiscountManagerTests extends TestCase {

	private static DiscountManager manager = null;
	private static UserManager userManager = null;
	private static BillManager billManager = null;
	
	static{
		try {
			manager = BillingSystem.getSystemManager().getDiscountManager();
			userManager = BillingSystem.getSystemManager().getUserManager();
			billManager = BillingSystem.getSystemManager().getBillManager();
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}
	
	public void testGetValidBillDiscount() {
		BillDiscount discount = manager.getBillDiscount();
		assertNotNull(discount);
		assertEquals(5f, discount.getDiscount());
		assertEquals(100f, discount.getBase());
	}

	public void testGetValidItemDiscount() {
		ItemDiscount discount = manager.getItemDiscount(ItemType.BOOKS,
				UserType.EMPLOYEE);
		assertNotNull(discount);
		assertEquals(ItemType.BOOKS, discount.getItemType());
		assertEquals(UserType.EMPLOYEE, discount.getUserType());
		assertEquals(30f, discount.getPercentDiscount());
		assertEquals(0f, discount.getValidFrom());
	}

	public void testGetInvalidItemDiscount() {
		ItemDiscount discount = manager.getItemDiscount(ItemType.APPARELS, UserType.AFFILIATE);
		assertEquals(0f, discount.getPercentDiscount());
		assertEquals(0f, discount.getValidFrom());
		assertEquals(ItemType.APPARELS, discount.getItemType());
		assertEquals(UserType.AFFILIATE, discount.getUserType());

	}
	
	public void testGetItemDiscountPercentForEmployee(){
		User employee = null;
		try {
			employee = userManager.getUser("00U1");
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(UserType.EMPLOYEE, employee.getType());
		float discount = manager.getItemDiscountPercent(ItemType.BOOKS, employee);
		assertEquals(30f, discount);
	}

	public void testGetItemDiscountPercentForAffliate(){
		User affiliate = null;
		try {
			affiliate = userManager.getUser("00U2");
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(UserType.AFFILIATE, affiliate.getType());
		float discount = manager.getItemDiscountPercent(ItemType.BOOKS, affiliate);
		assertEquals(10f, discount);
	}

	public void testGetItemDiscountPercentForOldCustomer(){
		User customer = null;
		try {
			customer = userManager.getUser("00U3");
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(UserType.CUSTOMER, customer.getType());
		float discount = manager.getItemDiscountPercent(ItemType.BOOKS, customer);
		assertEquals(5f, discount);
	}
	
	public void testGetItemDiscountPercentForNewCustomer(){
		User customer = null;
		try {
			customer = userManager.getUser("00U5");
		} catch (UserNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(UserType.CUSTOMER, customer.getType());
		float discount = manager.getItemDiscountPercent(ItemType.BOOKS, customer);
		assertEquals(0f, discount);
	}
	
	public void testGetBillDiscountValue(){
		try {
			Bill bill = billManager.getBill("00B1");
			float grossPrice = bill.getGrossPrice();
			float discount = manager.getBillDiscountValue(grossPrice);
			assertEquals(bill.getDiscount(), discount);
		} catch (BillNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	public void testGetBillNODiscountValue(){
		try {
			Bill bill = billManager.getBill("00B4");
			float grossPrice = bill.getGrossPrice();
			float discount = manager.getBillDiscountValue(grossPrice);
			assertEquals(0f, discount);
			assertEquals(bill.getDiscount(), discount);
		} catch (BillNotFoundException e) {
			fail(e.getMessage());
		}
	}
}
