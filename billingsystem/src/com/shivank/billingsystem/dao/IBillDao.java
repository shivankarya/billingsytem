package com.shivank.billingsystem.dao;

import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillNotFoundException;

/**
 * Interface to provide various data access operations for a bill. 
 */
public interface IBillDao {

	/**
	 * Returns a bill from the database for a given bill id.
	 * @param billId bill id
	 * @return Bill 
	 * @throws BillNotFoundException If bill not found for a given id.
	 */
	public Bill getBill(String billId) throws BillNotFoundException;
	
}
