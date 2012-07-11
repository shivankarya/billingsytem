package com.shivank.billingsystem.bill;

import com.shivank.billingsystem.ManagerException;

/**
 * This exception class is used if the given bill is invalid.
 * For example, invalid user, items etc.
 */
public class InvalidBillException extends ManagerException {

	private static final long serialVersionUID = 1L;

	public InvalidBillException(Exception e){
		super(e);
	}
	
	@Override
	public String getMessage() {
		return "Error while creating a bill";
	}

	
}
