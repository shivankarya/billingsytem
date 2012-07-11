package com.shivank.billingsystem.test.dao.seeded;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.shivank.billingsystem.dao.IUserDao;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserNotFoundException;
import com.shivank.billingsystem.user.UserType;

/**
 * Seeded Users DAO 
 */
public class SeededUserDao implements IUserDao {

	private static final Map<String, User> users = new HashMap<String, User>();

	static {
		try {
			users.put("00U1", new User("00U1", "User1", UserType.EMPLOYEE,
					new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2006")));
			users.put("00U2", new User("00U2", "User2", UserType.AFFILIATE,
					new SimpleDateFormat("dd/MM/yyyy").parse("18/01/2012")));
			users.put("00U3", new User("00U3", "User3", UserType.CUSTOMER,
					new SimpleDateFormat("dd/MM/yyyy").parse("07/06/2008")));
			users.put("00U4", new User("00U4", "User4", UserType.EMPLOYEE,
					new SimpleDateFormat("dd/MM/yyyy").parse("11/09/2011")));
			users.put("00U5", new User("00U5", "User5", UserType.CUSTOMER,
					new SimpleDateFormat("dd/MM/yyyy").parse("21/03/2012")));
			users.put("00U6", new User("00U6", "User6", UserType.AFFILIATE,
					new SimpleDateFormat("dd/MM/yyyy").parse("11/09/1998")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		User user = users.get(userId);

		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		return user;
	}
}
