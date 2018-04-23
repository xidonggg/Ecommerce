package com.service;

import java.util.List;

import com.po.OrderItem;

public interface IOrderItemService {
	public List<OrderItem> findAll();
	public boolean update(OrderItem orderItem);
	public boolean delete(int orderItemid);
	public OrderItem find(int orderItemid);
	public boolean save(OrderItem orderItem);
	public boolean save(List<OrderItem> orderItemlist);
}
