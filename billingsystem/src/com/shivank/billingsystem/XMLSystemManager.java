package com.shivank.billingsystem;

import com.shivank.billingsystem.bill.BillManager;
import com.shivank.billingsystem.dao.DAOException;
import com.shivank.billingsystem.dao.xml.XMLBillDao;
import com.shivank.billingsystem.dao.xml.XMLDiscountDao;
import com.shivank.billingsystem.dao.xml.XMLItemDao;
import com.shivank.billingsystem.dao.xml.XMLUserDao;
import com.shivank.billingsystem.discount.DiscountManager;
import com.shivank.billingsystem.item.ItemManager;
import com.shivank.billingsystem.user.UserManager;

/**
 * This class provides managers for different billing system objects which are connected.
 * to XML Data Access Objects. 
 */	
public class XMLSystemManager implements ISystemManager {

	private UserManager userManager;
	private ItemManager itemManager;
	private DiscountManager discountManager;
	private BillManager billManager;

	private String path;
	
	/**
	 * Instantiates System Manager with path to the folder containing XML data files.
	 * @param path Path to the folder containing XML data files.
	 */
	public XMLSystemManager(String path) {
		this.path = path;
	}
	
	/**
	 * Instantiates System Manager with default path to the folder containing XML data files.
	 */
	public XMLSystemManager(){
		this("data");
	}

	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getUserManager()
	 */
	@Override
	public UserManager getUserManager() throws ManagerException {
		if(userManager == null){
			try {
				userManager = new UserManager(new XMLUserDao(path+"/users.xml"));
			} catch (DAOException e) {
				throw new ManagerException(e);
			}
		}
		return this.userManager;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getItemManager()
	 */
	@Override
	public ItemManager getItemManager() throws ManagerException {
		if(itemManager == null){
			try {
				itemManager = new ItemManager(new XMLItemDao(path+"/items.xml"));
			} catch (DAOException e) {
				throw new ManagerException(e);
			}
		}
		return this.itemManager;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getDiscountManager()
	 */
	@Override
	public DiscountManager getDiscountManager() throws ManagerException {
		if(discountManager == null){
			try {
				discountManager  = new DiscountManager(new XMLDiscountDao(path+"/itemdiscounts.xml", "data/billdiscount.xml"));
			} catch (DAOException e) {
				throw new ManagerException(e);
			}
		}
		return this.discountManager;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.ISystemManager#getBillManager()
	 */
	@Override
	public BillManager getBillManager() throws ManagerException {
		if(billManager == null){
			try {
				billManager  = new BillManager(new XMLBillDao(path+"/bills.xml"));
			} catch (DAOException e) {
				throw new ManagerException(e);
			}
		}
		return this.billManager;
	}
}