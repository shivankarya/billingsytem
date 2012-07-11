package com.shivank.billingsystem.bill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ISystemManager;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserNotFoundException;

/**
 * This class is an immutable representation of a bill.
 * 
 * It provides various getter methods to access it's properties.
 * 
 * The Net Price and Discounts are calculated as when items are added to the bill. 
 * 
 */
public final class Bill {

	private String billId;
	private String userId;
	private Date date;
	private List<BillItem> billItems = new ArrayList<BillItem>();
	private float grossPrice = 0;
	private float discount;
	private float netPrice;

	private User user;

	/**
	 * Creates a bill with no bill items in it.
	 * 
	 * @param billId bill id
	 * @param userId user id
	 * @param date date of bill creation
	 * @throws InvalidBillException If the bill provided is an invalid one.
	 * @throws ManagerException If problem occurs while Manager layer.s
	 */
	public Bill(String billId, String userId, Date date)
			throws InvalidBillException, ManagerException {
		try {
			this.billId = billId;
			this.userId = userId;
			this.user = BillingSystem.getSystemManager().getUserManager().getUser(userId);
			this.date = new Date(date.getTime());
		} catch (UserNotFoundException e) {
			throw new InvalidBillException(e);
		}
	}

	/**
	 * Creates a bill with initial list of items.
	 * 
	 * @param billId Bill id
	 * @param userId User id
	 * @param date Date of bill
	 * @param items Bill Items
	 * @throws InvalidBillException If the provided bill id is invalid.
	 * @throws ManagerException If problem occurs in Manager layer.
	 */
	public Bill(String billId, String userId, Date date, List<BillItem> items)
			throws InvalidBillException, ManagerException {
		try {
			this.billId = billId;
			this.userId = userId;
			this.user = BillingSystem.getSystemManager().getUserManager().getUser(userId);
			this.date = new Date(date.getTime());
			billItems = new ArrayList<BillItem>(items);
			
			// Calculate net price of each bill item.
			// And add it to Bill's gross price.
			for (BillItem item : billItems) {
				grossPrice += item.getNetPrice();
			}

			// Calculate net price of the entire bill.
			calculateNetPrice();
		} catch (UserNotFoundException e) {
			throw new InvalidBillException(e);
		}

	}

	/**
	 * Adds an item to the bill
	 * @param itemId item id
	 * @param qty quantity of the item
	 * @throws ItemNotFoundException If provided item is not found in data source.
	 * @throws ManagerException If a problem occurs in manager layer.
	 */
	public void addItem(String itemId, int qty) throws ItemNotFoundException, ManagerException {
		BillItem item = new BillItem(this.user, itemId, qty);
		billItems.add(item);
		// Add item's net price to bill's gross price and recalculate the bill's net price.
		grossPrice += item.getNetPrice();
		calculateNetPrice();
	}

	/**
	 * Calculates bill's net price by applying bill discount to the gross price.
	 * 
	 * @throws ManagerException if any problem occurs in the manager layer.
	 */
	private void calculateNetPrice() throws ManagerException {
		ISystemManager systemManager = BillingSystem.getSystemManager();
		discount = systemManager.getDiscountManager().getBillDiscountValue(grossPrice);
		netPrice = grossPrice - discount;
	}

	/**
	 * Builds the UI representation of the bill.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("----------Shivank Billing System---------\n");
		sb.append("Bill Id: ").append(billId).append("\n");
		sb.append("Bill Date: ").append(date).append("\n");
		sb.append(user).append("\n");
		sb.append("----------Items---------\n");
		float savings = 0;
		for (BillItem item : billItems) {
			sb.append(item).append("\n");
			savings += item.getDiscount();
		}
		savings += discount;
		sb.append("-----------------------------\n");
		sb.append("Gross Price: ").append(grossPrice).append("\n");
		sb.append("Bill Discount: ").append(discount).append("\n");
		sb.append("Total Savings: ").append(savings).append("\n");
		sb.append("Net Price: ").append(netPrice).append("\n");

		return sb.toString();
	}

	public String getBillId() {
		return billId;
	}

	public String getUserId() {
		return userId;
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public List<BillItem> getBillItems() {
		return Collections.unmodifiableList(billItems);
	}

	public float getGrossPrice() {
		return grossPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public float getNetPrice() {
		return netPrice;
	}
}
