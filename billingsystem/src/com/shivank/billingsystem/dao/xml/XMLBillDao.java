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

import com.shivank.billingsystem.BillingSystemException;
import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillNotFoundException;
import com.shivank.billingsystem.dao.DAOException;
import com.shivank.billingsystem.dao.IBillDao;

/**
 * XML Data Access Object for Bills
 */
public class XMLBillDao implements IBillDao{

	private Map<String, Bill> bills = new HashMap<String, Bill>();
	
	/**
	 * Instantiates the object and reads bills from the given path.
	 * 
	 * @param path Path to Bill xml file.
	 * @throws DAOException If an error occurs while reading xml file.
	 */
	public XMLBillDao(String path) throws DAOException{
		try {
			//Read the bill xml file.
			Document doc = XMLUtils.getXMLDocument(path);
			// Get bill elements.
			NodeList billList = doc.getElementsByTagName("bill");
			for (int s = 0; s < billList.getLength(); s++) {
			    Element bill = (Element)billList.item(s);
			    String id = bill.getElementsByTagName("id").item(0).getTextContent();
			
			    String userId = bill.getElementsByTagName("userid").item(0).getTextContent();
			    
			    String date = bill.getElementsByTagName("date").item(0).getTextContent();
			    Date billDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			    
			    Bill currBill = new Bill(id, userId, billDate);
			    
			    // Get bill items
			    NodeList itemList = bill.getElementsByTagName("item");
			    for (int t = 0; t < itemList.getLength(); t++) {
			    	Element item = (Element)itemList.item(t);
			    	String itemId = item.getElementsByTagName("id").item(0).getTextContent();
			    	String qty = item.getElementsByTagName("qty").item(0).getTextContent();
			    	currBill.addItem(itemId, Integer.parseInt(qty));
			    }			    
			    
			    bills.put(id, currBill);
			}
		} catch (ParserConfigurationException e) {
			throw new DAOException(e);
		} catch (SAXException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (ParseException e) {
			throw new DAOException(e);
		} catch (BillingSystemException e) {
			throw new DAOException(e);
		} 	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shivank.billingsystem.dao.IBillDao#getBill(java.lang.String)
	 */
	@Override
	public Bill getBill(String billId) throws BillNotFoundException {
		Bill bill = bills.get(billId);		
		if(bill == null){
			throw new BillNotFoundException(billId);
		}
		return bill;
	}
}
