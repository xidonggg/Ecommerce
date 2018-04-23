package com.dao;

import java.util.List;

import com.po.Order;
import com.po.OrderItem;

public interface IOrderItemDAO {
	public boolean save(OrderItem orderItem);
	public boolean update(OrderItem orderItem);
	public OrderItem find(int orderItemid);
	public boolean delete(int orderItemid);
	public List<OrderItem> findAll();
	public List<OrderItem> findbyOrder(Order order);//根据Order查找所有订单项
	//public List<OrderItem> findbyOrderAState(Order order,String state);//根据Order和订单状态查找所有订单项
}
