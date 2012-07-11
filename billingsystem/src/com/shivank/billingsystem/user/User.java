package com.shivank.billingsystem.user;

import java.util.Date;

/**
 * This class represents an immutable User object
 */
public final class User {
	
	private String id;
	private String name;
	private String address;
	private String phone;
	private UserType type;
	private Date doj;
	
	private static final String NOT_SPECIFIED = "Not Specified";
	
	public User(String id, String name, String address, String phone, UserType type, Date doj){
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.type = type;
		this.doj = new Date(doj.getTime());
	}
	
	public User(String id, String name, UserType type, Date doj){
		this(id, name, NOT_SPECIFIED, NOT_SPECIFIED, type, new Date(doj.getTime()));
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public UserType getType() {
		return type;
	}

	public Date getDoj() {
		return new Date(doj.getTime());
	}

	public String getPhone() {
		return phone;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("UserId: ").append(id).append("\t");
		sb.append("Name: ").append(name);
		
		return sb.toString();
	}
}
