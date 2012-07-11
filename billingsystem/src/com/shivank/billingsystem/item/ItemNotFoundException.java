package com.shivank.billingsystem.item;

import java.text.MessageFormat;

import com.shivank.billingsystem.ManagerException;

/**
 * Exception class used in case if item for a given id is Not Found.
 */
public class ItemNotFoundException extends ManagerException {

	private static final long serialVersionUID = 1L;
	
	private String itemId;

	public ItemNotFoundException(String itemId){
		this.itemId = itemId;
	}
	
	public ItemNotFoundException(String itemId, Exception e){
		super(e);
		this.itemId = itemId;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format("Item {0} not found", itemId);
	}
}
