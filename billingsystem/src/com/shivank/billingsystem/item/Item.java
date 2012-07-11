package com.shivank.billingsystem.item;

/**
 * This class is an immutable representation of an Item
 */
public final class Item {

	private String id;
	private String name;
	private String description;
	private ItemType type;
	private float price;
	
	private static final String NOT_SPECIFIED = "Not Specified";
	
	public Item(String id, String name, String description, ItemType type, float price){
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
	}
	
	public Item(String id, String name, ItemType type, float price){
		this(id, name, NOT_SPECIFIED, type, price);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ItemType getType() {
		return type;
	}

	public float getPrice() {
		return price;
	}
	
	
}
