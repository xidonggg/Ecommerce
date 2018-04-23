package com.po;



public class OrderItem {
	private int id;
	private int needNum;
	private Order order;
	private Commodity commodity;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public int getNeedNum() {
		return needNum;
	}
	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
