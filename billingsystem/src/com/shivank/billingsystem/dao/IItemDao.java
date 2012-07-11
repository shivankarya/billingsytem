package com.shivank.billingsystem.dao;

import com.shivank.billingsystem.item.Item;
import com.shivank.billingsystem.item.ItemNotFoundException;

/**
 * Interface to provide Item related database operations.
 */
public interface IItemDao {

	/**
	 * Returns an item for a given itemid from the database
	 * @param itemId item id
	 * @return Item
	 * @throws ItemNotFoundException If an item is Not found for a given id.
	 */
	public Item getItem(String itemId) throws ItemNotFoundException;
}
