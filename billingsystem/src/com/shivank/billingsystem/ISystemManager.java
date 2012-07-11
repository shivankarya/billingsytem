package com.shivank.billingsystem;

import com.shivank.billingsystem.bill.BillManager;
import com.shivank.billingsystem.discount.DiscountManager;
import com.shivank.billingsystem.item.ItemManager;
import com.shivank.billingsystem.user.UserManager;

/**
 * System Manager Interface provides an handle to other Managers which hold the
 * business logic of different entities of Billing System.
 * 
 * These managers provide business logic for various entities and abstract the
 * Data Access Layer from the business layer.
 */
public interface ISystemManager {
	/**
	 * Provides an handle to User Manager
	 * 
	 * User Manager handles various User related operations.
	 * 
	 * @return an instance of UserManager
	 * @throws ManagerException
	 *             In case an error occurs while getting user manager.
	 */
	public UserManager getUserManager() throws ManagerException;

	/**
	 * Provides an handle to Item Manager
	 * 
	 * Item Manager handles various Item related operations.
	 * 
	 * @return an instance of ItemManager
	 * @throws ManagerException
	 *             In case an error occurs while getting item manager.
	 */
	public ItemManager getItemManager() throws ManagerException;

	/**
	 * Provides an handle to Discount Manager
	 * 
	 * Discount Manager handles various Discount related operations.
	 * 
	 * @return an instance of DiscountManager
	 * @throws ManagerException
	 *             In case an error occurs while getting Discount manager.
	 */
	public DiscountManager getDiscountManager() throws ManagerException;

	/**
	 * Provides an handle to Bill Manager
	 * 
	 * Bill Manager handles various Bill related operations.
	 * 
	 * @return an instance of BillManager
	 * @throws ManagerException
	 *             In case an error occurs while getting Bill manager.
	 */
	public BillManager getBillManager() throws ManagerException;
}
