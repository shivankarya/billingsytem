package com.shivank.billingsystem;

import com.shivank.billingsystem.bill.Bill;
import com.shivank.billingsystem.bill.BillManager;

/**
 * Main class of Billing System.
 * 
 * The Billing System Application is executed using this class.
 *
 */
public class BillingSystem {

	private static ISystemManager systemManager = Mode.XML.getManager("data");

	/**
	 * Returns the System Manager set while executing 
	 * @return Configured System Manager of the application.
	 */
	public static ISystemManager getSystemManager() {
		return systemManager;
	}

	/**
	 * Sets the System Manager for the application.
	 * @param manager System Manager
	 */
	public static void setSystemManager(ISystemManager manager) {
		systemManager = manager;
	}

	/**
	 * Main Program for Billing System
	 * @param args input parameters for the application
	 */
	public static void main(String args[]) {
		try {		
			// Parse arguments to get the Bill Number to be evaluated.
			// Also, to check if the application needs to read data from XML or SEEDED data source 
			Mode mode = Mode.XML;
			String path = "data";
			if(args.length < 2 || args.length > 6 || args.length%2 != 0 || !args[0].equalsIgnoreCase("-b")){
				System.out.println(usage());
				return;
			}else if(args.length == 4 && args[2].equalsIgnoreCase("-m")) {
				mode = Mode.valueOf(args[3].toUpperCase());
			} else if (mode == Mode.XML && args.length == 6 && args[4].equalsIgnoreCase("-p")) {
				path = args[5];
			}
			setSystemManager(mode.getManager(path));
			String billNumber = args[1];
			
			//Build the bill object and this will evaluate it's net price as well.
			BillManager billManager = getSystemManager().getBillManager();
			Bill bill = billManager.getBill(billNumber);
			System.out.println(bill);			
		} catch (BillingSystemException e) {
			e.printStackTrace();
		}
	}
	
	public static String usage(){
		StringBuilder sb = new StringBuilder();
		sb.append("----------USAGE----------\n");
		sb.append("java BillingSystem -b <billnumber> -m <mode> -p <path> \n");
		sb.append("-b <billnumber> [MANDATORY] Bill number for which Net Price should be calculated. \n");
		sb.append("\t For example: -b B001 \n");
		sb.append("-m <mode> [OPTIONAL] Data Access Objects - seeded or xml. DEFAULT: xml \n");
		sb.append("\t For example: -m seeded OR -m xml \n");
		sb.append("-p <path> [OPTIONAL] Path to directory containing data XML files DEFAULT: data folder in project \n");
		sb.append("\t For example: -p c:/data \n");
		
		return sb.toString();
	}
}
