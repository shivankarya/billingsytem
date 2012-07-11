package com.shivank.billingsystem.bill;

import com.shivank.billingsystem.dao.IBillDao;

/**
 * This class is used for abstracting data access layer from business layer.
 * Also, to provide common business logic (if any) on top of any data access
 * layer like pre and post processing.
 * 
 */
public class BillManager {

	private IBillDao billDao;

	public BillManager(IBillDao billDao) {
		this.billDao = billDao;
	}

	public Bill getBill(String billId) throws BillNotFoundException {
		return billDao.getBill(billId);
	}
}
