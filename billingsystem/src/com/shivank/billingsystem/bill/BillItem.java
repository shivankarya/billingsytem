package com.shivank.billingsystem.bill;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ISystemManager;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.item.Item;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.user.User;

public final class BillItem {

	private String itemId;
	private int qty;
	private float grossPrice;
	private float discount;
	private float netPrice;
	
	private Item item;
	
	public BillItem(User user, String itemId, int qty) throws ItemNotFoundException, ManagerException{
		this.itemId = itemId;
		this.qty = qty;
		
		ISystemManager systemManager = BillingSystem.getSystemManager(); 
		item = systemManager.getItemManager().getItem(itemId);
		grossPrice = item.getPrice()*qty;
		discount = systemManager.getDiscountManager().getItemDiscountValue(item.getType(), grossPrice, user);		
		netPrice = grossPrice - discount;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Itemid: ").append(getItemId()).append("\t");
		sb.append("Name: ").append(item.getName()).append("\t");
		sb.append("Quantity: ").append(getQty()).append("\t");
		sb.append("Gross Price: ").append(getGrossPrice()).append("\t");
		sb.append("Discount: ").append(getDiscount()).append("\t");
		sb.append("Net Price: ").append(getNetPrice());
		
		return sb.toString();
	}
		
	public String getItemId() {
		return itemId;
	}
	public int getQty() {
		return qty;
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
