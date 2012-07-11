package com.shivank.billingsystem.test.item;

import junit.framework.TestCase;

import com.shivank.billingsystem.BillingSystem;
import com.shivank.billingsystem.ManagerException;
import com.shivank.billingsystem.item.Item;
import com.shivank.billingsystem.item.ItemManager;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.item.ItemType;

/**
 * This class contains tests related to items.
 */
public class ItemManagerTests extends TestCase{

	private static ItemManager manager = null;
	
	static{
		try {
			manager = BillingSystem.getSystemManager().getItemManager();
		} catch (ManagerException e) {
			fail(e.getMessage());
		}
	}

	public void testGetValidItem(){
		try {
			Item item = manager.getItem("00I1");
			assertNotNull(item);
			assertEquals("00I1", item.getId());
		} catch (ItemNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	public void testVaildItemDetails(){
		try {
			Item item = manager.getItem("00I1");
			assertNotNull(item);
			assertEquals("00I1", item.getId());
			assertEquals("Item1", item.getName());
			assertEquals(ItemType.GROCERY, item.getType());			
			assertEquals(25f, item.getPrice());			
		} catch (ItemNotFoundException e) {
			fail(e.getMessage());
		}		
	}

	public void testGetInvalidItem(){
		try {
			manager.getItem("Invalid");
			fail("Expected exception as provided Item number is invalid");
		} catch (ItemNotFoundException e) {
			//Success Scenario
		}
	}	
}
