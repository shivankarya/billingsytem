package com.shivank.billingsystem;

/**
 * Top-level Exception of Billing System
 * 
 *  All other exceptions in the application extend this exception. 
 */
public class BillingSystemException extends Exception{

	public BillingSystemException(){
		
	}
	
	public BillingSystemException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
