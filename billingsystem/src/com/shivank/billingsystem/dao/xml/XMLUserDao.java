package com.shivank.billingsystem.dao.xml;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.shivank.billingsystem.dao.DAOException;
import com.shivank.billingsystem.dao.IUserDao;
import com.shivank.billingsystem.user.User;
import com.shivank.billingsystem.user.UserNotFoundException;
import com.shivank.billingsystem.user.UserType;

/**
 * XML Data Access Object for Users.
 */
public class XMLUserDao implements IUserDao {

	/**
	 * Maintains users
	 */
	private Map<String, User> users = new HashMap<String, User>();

	/**
	 * Builds DAO object and reads users from the provided xml file
	 * @param path path to users xml file
	 * 
	 * @throws DAOException If any problem occurs while working on xml file.
	 */
	public XMLUserDao(String path) throws DAOException{
		try {
			Document doc = XMLUtils.getXMLDocument(path);
			NodeList userList = doc.getElementsByTagName("user");
			for (int s = 0; s < userList.getLength(); s++) {
			    Element user = (Element)userList.item(s);
			    String id = user.getElementsByTagName("id").item(0).getTextContent();
			
			    String name = user.getElementsByTagName("name").item(0).getTextContent();
			    
			    String type = user.getElementsByTagName("type").item(0).getTextContent();
			    UserType userType = UserType.valueOf(type);
			    
			    String doj = user.getElementsByTagName("doj").item(0).getTextContent();
			    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(doj);
			    
			    users.put(id, new User(id, name, userType, date));
			}
		} catch (ParserConfigurationException e) {
			throw new DAOException(e);
		} catch (SAXException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (ParseException e) {
			throw new DAOException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.dao.IUserDao#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userId) throws UserNotFoundException {
		User user = users.get(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		return user;
	}
}