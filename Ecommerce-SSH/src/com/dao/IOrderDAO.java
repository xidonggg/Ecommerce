package com.dao;

import java.util.List;

import com.po.Order;
import com.po.User;

public interface IOrderDAO {
	public boolean save(Order order);//����
	public boolean delete(int orderid);
	public boolean update(Order order);
	public Order find(int orderid);
	public List<Order> findAll();
	public List<Order> find(User user);//�����û�����
	public List<Order> findbyState(String state);//����״̬����
	public List<Order> findbyAddress(String address);//�����ջ���ַģ������
}
