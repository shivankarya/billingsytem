package com.shivank.billingsystem;

/**
 * Main class for Manager related exceptions.
 * 
 * This class handles problem in business layer and encapsulates exceptions
 * thrown by data access layer (if any).
 */
public class ManagerException extends BillingSystemException {

	private static final long serialVersionUID = 1L;

	public ManagerException() {

	}

	public ManagerException(Exception e) {
		super(e);
	}

}