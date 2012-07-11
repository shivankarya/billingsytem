package com.shivank.billingsystem.dao;

import com.shivank.billingsystem.BillingSystemException;

/**
 * Root exception class for Data Access Objects.
 */
public class DAOException extends BillingSystemException {

	private static final long serialVersionUID = 1L;
	
	public DAOException(Exception e){
		super(e);
	}

	@Override
	public String getMessage() {
		return "Error while reading database";
	}
}
