package com.shivank.billingsystem.test.dao.seeded;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillNotFoundException;
import com.shivank.billingsystem.bill.InvalidBillException;
import com.shivank.billingsystem.dao.IBillDao;
import com.shivank.billingsystem.item.ItemNotFoundException;

/**
 * Seeded Bill DAO 
 */
public class SeededBillDao implements IBillDao {

	private static final Map<String, Bill> bills = new HashMap<String, Bill>();

	static {
		try {
			Bill bill1 = new Bill("00B1", "00U1", Calendar.getInstance().getTime());
			bill1.addItem("00I1", 5);
			bill1.addItem("00I2", 10);
			bills.put(bill1.getBillId(), bill1);
			
			bill1 = new Bill("00B2", "00U2", Calendar.getInstance().getTime());
			bill1.addItem("00I1", 5);
			bill1.addItem("00I2", 10);
			bills.put(bill1.getBillId(), bill1);
			
			bill1 = new Bill("00B3", "00U3", Calendar.getInstance().getTime());
			bill1.addItem("00I1", 5);
			bill1.addItem("00I2", 10);
			bills.put(bill1.getBillId(), bill1);
			
			bill1 = new Bill("00B4", "00U5", Calendar.getInstance().getTime());
			bill1.addItem("00I1", 1);
			bill1.addItem("00I2", 1);
			bills.put(bill1.getBillId(), bill1);
			
		} catch (InvalidBillException e) {
			e.printStackTrace();
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bill getBill(String billId) throws BillNotFoundException {
		Bill bill = bills.get(billId);		
		if(bill == null){
			throw new BillNotFoundException(billId);
		}
		return bill;
	}

}
