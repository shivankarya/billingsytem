package com.shivank.billingsystem.discount;

import com.shivank.billingsystem.item.ItemType;
import com.shivank.billingsystem.user.UserType;

/**
 * This class is an immutable representation of a item discount
 */
public final class ItemDiscount {
	private UserType ut;
	private ItemType it;
	private float percentDiscount;
	private float validFrom;
	
	public ItemDiscount(UserType ut, ItemType it, float percentDiscount, float validFrom){
		this.ut = ut;
		this.it = it;
		this.percentDiscount = percentDiscount;
		this.validFrom = validFrom;
	}

	public UserType getUserType() {
		return ut;
	}

	public ItemType getItemType() {
		return it;
	}

	public float getPercentDiscount() {
		return percentDiscount;
	}

	public float getValidFrom() {
		return validFrom;
	}
}
