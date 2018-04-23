package com.po;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String phonenumber;
	private String address;
	private Date time;
	private String state;
	private User user;
	private int useScore;
	private Set<OrderItem> orderItemlist = new HashSet<OrderItem>(0);
	public Order(){
		this.state = "´ý·¢»õ";
	}
	public int getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItemlist() {
		return orderItemlist;
	}
	public void setOrderItemlist(Set<OrderItem> orderItemlist) {
		this.orderItemlist = orderItemlist;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUseScore() {
		return useScore;
	}
	public void setUseScore(int useScore) {
		this.useScore = useScore;
	}

}
