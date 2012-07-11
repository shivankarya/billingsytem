package com.shivank.billingsystem.test.dao.seeded;

import java.util.HashMap;
import java.util.Map;

import com.shivank.billingsystem.dao.IItemDao;
import com.shivank.billingsystem.item.Item;
import com.shivank.billingsystem.item.ItemNotFoundException;
import com.shivank.billingsystem.item.ItemType;

/**
 * Seeded Item DAO 
 */
public class SeededItemDao implements IItemDao {

	private static Map<String, Item> items = new HashMap<String, Item>();
	
	static{
		items.put("00I1", new Item("00I1", "Item1", ItemType.GROCERY, 25));
		items.put("00I2", new Item("00I2", "Item2", ItemType.APPARELS, 30));
		items.put("00I3", new Item("00I3", "Item3", ItemType.BOOKS, 50));
		items.put("00I4", new Item("00I4", "Item4", ItemType.GROCERY, 55));
		items.put("00I5", new Item("00I5", "Item5", ItemType.GROCERY, 75));
		items.put("00I6", new Item("00I6", "Item6", ItemType.OTHERS, 100));
	}
	
	@Override
	public Item getItem(String itemId) throws ItemNotFoundException {
		Item item = items.get(itemId);
		if (item == null) {
			throw new ItemNotFoundException(itemId);
		} 
		return item;
	}

}
