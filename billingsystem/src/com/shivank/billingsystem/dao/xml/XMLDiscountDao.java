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
import com.shivank.billingsystem.dao.IDiscountDao;
import com.shivank.billingsystem.discount.BillDiscount;
import com.shivank.billingsystem.discount.ItemDiscount;
import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.UserType;

/**
 * XML Data Access Object for Discounts
 */
public class XMLDiscountDao implements IDiscountDao {

	/**
	 * Maintains item discounts
	 */
	private Map<String, ItemDiscount> itemDiscounts = new HashMap<String, ItemDiscount>();
	
	/**
	 * Maintains bill discounts.
	 */
	private BillDiscount billDiscount;

	/**
	 * Instantiates Discount and reads the item discount and bill discount from the provided path
	 * 
	 * @param itemDiscountPath Path to item discount xml
	 * @param billDiscountPath Path to bill discount xml
	 * 
	 * @throws DAOException If an error occurs while working on provided xml files.
	 */
	public XMLDiscountDao(String itemDiscountPath, String billDiscountPath) throws DAOException{
		try {
			// Get item discounts
			Document doc = XMLUtils.getXMLDocument(itemDiscountPath);
			NodeList itemDiscountList = doc.getElementsByTagName("discount");
			for (int s = 0; s < itemDiscountList.getLength(); s++) {
			    Element itemDiscount = (Element)itemDiscountList.item(s);
			    
			    String ut = itemDiscount.getElementsByTagName("usertype").item(0).getTextContent();
			    UserType userType = UserType.valueOf(ut);
			    
			    String it = itemDiscount.getElementsByTagName("itemtype").item(0).getTextContent();
			    ItemType itemType = ItemType.valueOf(it);

			    String percentDiscount = itemDiscount.getElementsByTagName("percentdiscount").item(0).getTextContent();

			    String validFrom = itemDiscount.getElementsByTagName("validfrom").item(0).getTextContent();
			    
			    itemDiscounts.put(userType + "_" + itemType, new ItemDiscount(userType, itemType, Float.parseFloat(percentDiscount), Float.parseFloat(validFrom)));
			}
			
			// Get bill discount
			doc = XMLUtils.getXMLDocument(billDiscountPath);
			String discount = doc.getDocumentElement().getElementsByTagName("discount").item(0).getTextContent();
			String base = doc.getDocumentElement().getElementsByTagName("base").item(0).getTextContent();
			billDiscount = new BillDiscount(Float.parseFloat(discount), Float.parseFloat(base));
			
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
	 * @see com.shivank.billingsystem.dao.IDiscountDao#getBillDiscount()
	 */
	@Override
	public BillDiscount getBillDiscount() {
		return billDiscount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.dao.IDiscountDao#geItemDiscount(com.shivank.billingsystem.item.ItemType, com.shivank.billingsystem.user.UserType)
	 */
	@Override
	public ItemDiscount geItemDiscount(ItemType it, UserType ut) {
		ItemDiscount discount = itemDiscounts.get(ut+"_"+it);
		if (discount == null) {
			return new ItemDiscount(ut, it, 0, 0);
		} else {
			return discount;
		}
	}
}
