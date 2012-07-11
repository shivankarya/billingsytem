package com.shivank.billingsystem.user;

import com.shivank.billingsystem.dao.IUserDao;

/**
 * This class is used for abstracting data access layer from business layer.
 * 
 * Also, to provide common business logic (if any) on top of any data access
 * layer like pre and post processing.
 * 
 */
public class UserManager {

	private IUserDao userDao;
	
	public UserManager(IUserDao userDao){
		this.userDao = userDao;
	}
	
	/**
	 * Returns a user with a given id from data access layer.
	 * @param userId user id
	 * @return User object
	 * @throws UserNotFoundException If user with given id is not found.
	 */
	public User getUser(String userId) throws UserNotFoundException{
		return userDao.getUser(userId);
	}
}
