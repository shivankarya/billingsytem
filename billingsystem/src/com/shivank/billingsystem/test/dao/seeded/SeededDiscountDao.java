package com.shivank.billingsystem.test.dao.seeded;

import java.util.HashMap;
import java.util.Map;

import com.shivank.billingsystem.dao.IDiscountDao;
import com.shivank.billingsystem.discount.BillDiscount;
import com.shivank.billingsystem.discount.ItemDiscount;
import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.UserType;

/**
 * Seeded Discount DAO 
 */
public class SeededDiscountDao implements IDiscountDao{
	
	private static final Map<String, ItemDiscount> itemDiscounts = new HashMap<String, ItemDiscount>();
	
	private static final BillDiscount BILL_DISCOUNT = new BillDiscount(5, 100);
	
	static{
		itemDiscounts.put(UserType.EMPLOYEE+"_"+ItemType.BOOKS, new ItemDiscount(UserType.EMPLOYEE, ItemType.BOOKS, 30, 0));
		itemDiscounts.put(UserType.AFFILIATE+"_"+ItemType.BOOKS, new ItemDiscount(UserType.AFFILIATE, ItemType.BOOKS, 10, 0));
		itemDiscounts.put(UserType.CUSTOMER+"_"+ItemType.BOOKS, new ItemDiscount(UserType.CUSTOMER, ItemType.BOOKS, 5, 2));

		itemDiscounts.put(UserType.EMPLOYEE+"_"+ItemType.APPARELS, new ItemDiscount(UserType.EMPLOYEE, ItemType.APPARELS, 30, 0));
//		itemDiscounts.put(UserType.AFFILIATE+"_"+ItemType.APPARELS, new ItemDiscount(UserType.AFFILIATE, ItemType.APPARELS, 10, 0));
		itemDiscounts.put(UserType.CUSTOMER+"_"+ItemType.APPARELS, new ItemDiscount(UserType.CUSTOMER, ItemType.APPARELS, 5, 2));
		
		itemDiscounts.put(UserType.EMPLOYEE+"_"+ItemType.OTHERS, new ItemDiscount(UserType.EMPLOYEE, ItemType.OTHERS, 30, 0));
		itemDiscounts.put(UserType.AFFILIATE+"_"+ItemType.OTHERS, new ItemDiscount(UserType.AFFILIATE, ItemType.OTHERS, 10, 0));
		itemDiscounts.put(UserType.CUSTOMER+"_"+ItemType.OTHERS, new ItemDiscount(UserType.CUSTOMER, ItemType.OTHERS, 5, 2));
	}

	@Override
	public BillDiscount getBillDiscount() {
		return BILL_DISCOUNT;
	}

	@Override
	public ItemDiscount geItemDiscount(ItemType it, UserType ut) {
		ItemDiscount discount = itemDiscounts.get(ut+"_"+it);
		if (discount == null) {
			return new ItemDiscount(ut, it, 0, 0);
		} else {
			return discount;
		}
	}
}
