package com.shivank.billingsystem.discount;

/**
 * This class is an immutable representation of a bill discount
 */
public final class BillDiscount {

	private float discount;
	private float base;

	public BillDiscount(float discount, float base){
		this.discount = discount;
		this.base = base;
	}

	public float getDiscount() {
		return discount;
	}
	
	public float getBase(){
		return this.base;
	}
}
