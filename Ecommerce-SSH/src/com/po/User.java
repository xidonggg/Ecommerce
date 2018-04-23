package com.po;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private String phonenumber;
	private String address;
	private String email;
	private Date birthday;
	private int accScore;
	private Set<Commodity> commoditylist = new HashSet<Commodity>(0);
	private Set<CartItem> cartItemlist = new HashSet<CartItem>(0);
	private Set<Order> orderlist = new HashSet<Order>(0);
	private Set<LeaveMessage> leaveMessagelist = new HashSet<LeaveMessage>(0);
	public User(){
		this.id = 0;
		this.name = "сн©м";
		this.password = "123";
		this.phonenumber = "0";
		this.address = "0";
		this.email = "0";
		//this.accScore = 0;
		this.birthday = new Date();
	}
	public User(String name,String password,String phonenumber,String address,String email,Date birthday)
	{
		this.name = name;
		this.password = password;
		this.phonenumber = phonenumber;
		this.address = address;
		this.email = email;
		this.birthday = birthday;
		this.accScore = 0;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Commodity> getCommoditylist() {
		return commoditylist;
	}
	public void setCommoditylist(Set<Commodity> commoditylist) {
		this.commoditylist = commoditylist;
	}
	public Set<CartItem> getCartItemlist() {
		return cartItemlist;
	}
	public void setCartItemlist(Set<CartItem> cartItemlist) {
		this.cartItemlist = cartItemlist;
	}
	public Set<Order> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(Set<Order> orderlist) {
		this.orderlist = orderlist;
	}
	public Set<LeaveMessage> getLeaveMessagelist() {
		return leaveMessagelist;
	}
	public void setLeaveMessagelist(Set<LeaveMessage> leaveMessagelist) {
		this.leaveMessagelist = leaveMessagelist;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAccScore() {
		return accScore;
	}
	public void setAccScore(int accScore) {
		this.accScore = accScore;
	}

}
