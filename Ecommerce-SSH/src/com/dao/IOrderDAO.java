package com.dao;

import java.util.List;

import com.po.Order;
import com.po.User;

public interface IOrderDAO {
	public boolean save(Order order);//保存
	public boolean delete(int orderid);
	public boolean update(Order order);
	public Order find(int orderid);
	public List<Order> findAll();
	public List<Order> find(User user);//根据用户查找
	public List<Order> findbyState(String state);//根据状态查找
	public List<Order> findbyAddress(String address);//根据收货地址模糊查找
}
