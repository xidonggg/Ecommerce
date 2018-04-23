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
	public List<OrderItem> findbyOrder(Order order);//����Order�������ж�����
	//public List<OrderItem> findbyOrderAState(Order order,String state);//����Order�Ͷ���״̬�������ж�����
}
