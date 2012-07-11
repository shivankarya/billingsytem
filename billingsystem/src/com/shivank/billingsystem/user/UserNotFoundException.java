package com.shivank.billingsystem.user;

import java.text.MessageFormat;

import com.shivank.billingsystem.ManagerException;

/**
 * This exception class is used if user with given id is not found
 */
public class UserNotFoundException extends ManagerException {

	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public UserNotFoundException(String id){
		this.id = id;
	}
	
	public UserNotFoundException(String id, Exception e){
		super(e);
		this.id = id;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format("User {0} not found", id);
	}
}