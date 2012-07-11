package com.shivank.billingsystem;

import com.shivank.billingsystem.bill.BillManager;
import com.shivank.billingsystem.discount.DiscountManager;
import com.shivank.billingsystem.item.ItemManager;
import com.shivank.billingsystem.test.dao.seeded.SeededBillDao;
import com.shivank.billingsystem.test.dao.seeded.SeededDiscountDao;
import com.shivank.billingsystem.test.dao.seeded.SeededItemDao;
import com.shivank.billingsystem.test.dao.seeded.SeededUserDao;
import com.shivank.billingsystem.user.UserManager;

/**
 * This class provides managers for different billing system objects which
 * are connected to Seeded Data Source.
 * This is a Mock Setup where actual data access objects are replaced by seeded data.
 *
 * This class provides an excellent way of rapidly developing and testing the application
 * when actual data source is either unavailable or remote. 
 */	
public class SeededSystemManager implements ISystemManager{

	private UserManager userManager;
	private ItemManager itemManager;
	private DiscountManager discountManager;
	private BillManager billManager;
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getUserManager()
	 */
	public UserManager getUserManager(){
		if(userManager == null){
			userManager = new UserManager(new SeededUserDao());
		}
		return this.userManager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getItemManager()
	 */
	public ItemManager getItemManager(){
		if(itemManager == null){
			itemManager = new ItemManager(new SeededItemDao());
		}
		return this.itemManager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getDiscountManager()
	 */
	public DiscountManager getDiscountManager(){
		if(discountManager == null){
			discountManager  = new DiscountManager(new SeededDiscountDao());
		}
		return this.discountManager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getBillManager()
	 */
	public BillManager getBillManager(){
		if(billManager == null){
			billManager  = new BillManager(new SeededBillDao());
		}
		return this.billManager;
	}
}