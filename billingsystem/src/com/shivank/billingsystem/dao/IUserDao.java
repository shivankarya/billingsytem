package com.shivank.billingsystem.dao;

import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserNotFoundException;

/**
 * Interface to provide User related database operations.
 */
public interface IUserDao {

	/**
	 * Returns a user for a given id.
	 * @param userId user id
	 * @return User
	 * @throws UserNotFoundException If user for a given id is Not found in database.
	 */
	public User getUser(String userId) throws UserNotFoundException;

}
