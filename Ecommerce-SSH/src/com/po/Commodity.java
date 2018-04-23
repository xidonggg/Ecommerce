package com.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Commodity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String property;
	private String name;
	private double price;
	private int num;
	private String comdescribe;
	private String picsrc;
	private String state;
	private User user;
	private Set<CartItem> cartItemlist = new HashSet<CartItem>(0);
	private Set<LeaveMessage> leaveMessagelist = new HashSet<LeaveMessage>(0);
	public Commodity(){this.state = "´ýÉó²é";}
	public Commodity(String property,String name,double price,int num,String comdescribe,String picsrc,User user)
	{
		this.property = property;
		this.name = name;
		this.price = price;
		this.num = num;
		this.comdescribe = comdescribe;
		this.picsrc = picsrc;
		this.user = user;
		this.state = "´ýÉó²é";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComdescribe() {
		return comdescribe;
	}

	public void setComdescribe(String comdescribe) {
		this.comdescribe = comdescribe;
	}

	public String getPicsrc() {
		return picsrc;
	}

	public void setPicsrc(String picsrc) {
		this.picsrc = picsrc;
	}

	public Set<CartItem> getCartItemlist() {
		return cartItemlist;
	}

	public void setCartItemlist(Set<CartItem> cartItemlist) {
		this.cartItemlist = cartItemlist;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<LeaveMessage> getLeaveMessagelist() {
		return leaveMessagelist;
	}

	public void setLeaveMessagelist(Set<LeaveMessage> leaveMessagelist) {
		this.leaveMessagelist = leaveMessagelist;
	}
}
