package com.shivank.billingsystem.item;

import com.shivank.billingsystem.dao.IItemDao;

/**
 * This class is used for abstracting data access layer from business layer.
 * 
 * Also, to provide common business logic (if any) on top of any data access
 * layer like pre and post processing.
 * 
 */
public class ItemManager {

	private IItemDao itemDao;
	
	public ItemManager(IItemDao itemDao){
		this.itemDao = itemDao;
	}
	
	/**
	 * Gets an item for a given id from the data access object.
	 * @param itemId item id
	 * @return item
	 * @throws ItemNotFoundException If item for a given id is not found.
	 */
	public Item getItem(String itemId) throws ItemNotFoundException{
		return itemDao.getItem(itemId);
	}
}
