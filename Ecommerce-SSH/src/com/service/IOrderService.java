package com.service;


import java.util.List;

import com.po.Order;
import com.po.OrderItem;
import com.po.User;

public interface IOrderService {
	public boolean addOrder(Order order);//添加订单
	public boolean updateOrder(Order order,String state);//修改订单状态
	public boolean updateOrder(Order order);//修改订单
	public boolean addOrderItem(OrderItem orderItem);//添加订单项
	public Order find(int id);//查找订单
	public OrderItem findItem(int id);//查找订单项
	public List<Order>find(User user);//查找某人的所有订单
	public List<Order>findConcreteOrder();//把订单中的user信息补充完整
	public List<Order> findConcreteOrder(List<Order> orders);//把订单中的user信息补充完整
	public List<Order> findAllOrder(Order order);//条件查询订单
	public List<OrderItem> findAllOrderItem(Order order);//根据order查询订单项
	List<OrderItem> findConcreteOrderItem(List<OrderItem> orderItems);//完善orderIten信息
	List<OrderItem> findSellItem(User user);//卖家需完成的订单
	List<OrderItem> findSellItem(User user,String state);//卖家需完成的订单中根据状态查询
	
	public List<Order> findAllOrder();//查询所有订单
	public List<Order> findFailOrder();//查询买卖失败信息
	List<Order> findSell(User user);//卖家需完成的订单中根据状态查询
	List<Order> findSell(User user,String state);//卖家需完成的订单中根据状态查询
}
