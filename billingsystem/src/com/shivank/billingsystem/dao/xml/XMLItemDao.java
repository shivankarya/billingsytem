package com.shivank.billingsystem.dao.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.shivank.billingsystem.dao.DAOException;
import com.shivank.billingsystem.dao.IItemDao;
import com.shivank.billingsystem.item.Item;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.item.ItemType;

/**
 * XML Data Access Object for Items
 */
public class XMLItemDao implements IItemDao {

	/**
	 * Maintains the items.
	 */
	private Map<String, Item> items = new HashMap<String, Item>();
	
	/**
	 * Build DAO object and populates it with items from the given path
	 * @param path Path to items xml
	 * @throws DAOException If any problem occurs while working with xml
	 */
	public XMLItemDao(String path) throws DAOException{
		try {
			Document doc = XMLUtils.getXMLDocument(path);
			NodeList itemList = doc.getElementsByTagName("item");
			for (int s = 0; s < itemList.getLength(); s++) {
			    Element item = (Element)itemList.item(s);
			    String id = item.getElementsByTagName("id").item(0).getTextContent();
			
			    String name = item.getElementsByTagName("name").item(0).getTextContent();
			    
			    String type = item.getElementsByTagName("type").item(0).getTextContent();
			    ItemType itemType = ItemType.valueOf(type);
			    
			    String price = item.getElementsByTagName("price").item(0).getTextContent();
			    
			    items.put(id, new Item(id, name, itemType, Float.parseFloat(price)));
			}
		} catch (ParserConfigurationException e) {
			throw new DAOException(e);
		} catch (SAXException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.dao.IItemDao#getItem(java.lang.String)
	 */
	@Override
	public Item getItem(String itemId) throws ItemNotFoundException {
		Item item = items.get(itemId);
		if (item == null) {
			throw new ItemNotFoundException(itemId);
		} 
		return item;
	}

}
