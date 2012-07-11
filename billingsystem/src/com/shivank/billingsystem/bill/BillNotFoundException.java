package com.shivank.billingsystem.bill;

import java.text.MessageFormat;

import com.shivank.billingsystem.ManagerException;

/**
 * This exception class is used if a given bill id is Not Found in the data repository.
 *
 */
public class BillNotFoundException extends ManagerException {

	private static final long serialVersionUID = 1L;
	
	private String billId;

	public BillNotFoundException(String billId){
		this.billId = billId;
	}
	
	public BillNotFoundException(String billId, Exception e){
		super(e);
		this.billId = billId;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format("Bill {0} not found", billId);
	}
 
	
}
