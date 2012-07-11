package com.shivank.billingsystem.discount;

import java.util.Calendar;
import java.util.Date;

import com.shivank.billingsystem.dao.IDiscountDao;
import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserType;

/**
 * This class is used for abstracting data access layer from business layer.
 * 
 * Also, to provide common business logic (if any) on top of any data access
 * layer like pre and post processing.
 * 
 */
public class DiscountManager {

	private IDiscountDao discountDao;
	
	private static final long DAY_IN_MILL_SEC = 24*60*60*1000;
	
	public DiscountManager(IDiscountDao userDao){
		this.discountDao = userDao;
	}

	/**
	 * Returns bill discount
	 * @return bill discount
	 */
	public BillDiscount getBillDiscount(){
		return discountDao.getBillDiscount();
	}
	
	/**
	 * Returns item discount for a given item type and user type.
	 * 
	 * @param it item type
	 * @param ut user type
	 * @return item discount
	 */
	public ItemDiscount getItemDiscount(ItemType it, UserType ut){
		return discountDao.geItemDiscount(it, ut);
	}

	/**
	 * Returns the percentage of item discount.
	 * 
	 * If validFrom field for an item discount is maintained, it makes sure
	 * that User's date of joining satisfies the valid from condition to get discount.
	 * 
	 * @param it item type
	 * @param user user 
	 * @return discount percent.
	 */
	public float getItemDiscountPercent(ItemType it, User user) {
		ItemDiscount itemDiscount = getItemDiscount(it, user.getType());
		float validFrom = itemDiscount.getValidFrom();
		float percentDiscount = itemDiscount.getPercentDiscount();
		
		if(validFrom == 0) {
			return percentDiscount;
		}
		
		// If valid from date is specified in database, 
		// make sure that the user satisfies that condition.
		Date currDate = Calendar.getInstance().getTime();
		Date doj = user.getDoj();
		long diff = currDate.getTime() - doj.getTime();
		long year_diff = (diff/DAY_IN_MILL_SEC)/365;
		
		return (year_diff >= validFrom) ? percentDiscount : 0; 
	}
	

	/**
	 * Gets the item discount value
	 * 
	 * @param it item type
	 * @param grossPrice price on which discount should be applied
	 * @param user user
	 * @return discount value.
	 */
	public float getItemDiscountValue(ItemType it, float grossPrice, User user){
		float discountPercent = this.getItemDiscountPercent(it, user);
		return (discountPercent * grossPrice)/100;
	}
	
	/**
	 * Returns bill's discount value.
	 * 
	 * @param price Bill's gross price
	 * @return bill discount value.
	 */
	public float getBillDiscountValue(float price){
		BillDiscount billDiscount = getBillDiscount(); 
		float discount = billDiscount.getDiscount();
		float base = billDiscount.getBase();
		int discountFactor = (int) (price/base);
		return discount * discountFactor;
	}
}
