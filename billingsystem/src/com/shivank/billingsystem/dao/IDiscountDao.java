package com.shivank.billingsystem.dao;

import com.shivank.billingsystem.discount.BillDiscount;
import com.shivank.billingsystem.discount.ItemDiscount;
import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.UserType;

/**
 * Interface to provide database operations on various discounts.
 */
public interface IDiscountDao {

	/**
	 * Provides Bill Discount information from the data source.
	 * @return Bill Discount
	 */
	public BillDiscount getBillDiscount();
	
	/**
	 * Provides Item Discount information from the data source.
	 * @param it item type
	 * @param ut user type
	 * @return Item Discount
	 */
	public ItemDiscount geItemDiscount(ItemType it, UserType ut);
	
}
