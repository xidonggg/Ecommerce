package com.service;


import java.util.List;

import com.po.Order;
import com.po.OrderItem;
import com.po.User;

public interface IOrderService {
	public boolean addOrder(Order order);//��Ӷ���
	public boolean updateOrder(Order order,String state);//�޸Ķ���״̬
	public boolean updateOrder(Order order);//�޸Ķ���
	public boolean addOrderItem(OrderItem orderItem);//��Ӷ�����
	public Order find(int id);//���Ҷ���
	public OrderItem findItem(int id);//���Ҷ�����
	public List<Order>find(User user);//����ĳ�˵����ж���
	public List<Order>findConcreteOrder();//�Ѷ����е�user��Ϣ��������
	public List<Order> findConcreteOrder(List<Order> orders);//�Ѷ����е�user��Ϣ��������
	public List<Order> findAllOrder(Order order);//������ѯ����
	public List<OrderItem> findAllOrderItem(Order order);//����order��ѯ������
	List<OrderItem> findConcreteOrderItem(List<OrderItem> orderItems);//����orderIten��Ϣ
	List<OrderItem> findSellItem(User user);//��������ɵĶ���
	List<OrderItem> findSellItem(User user,String state);//��������ɵĶ����и���״̬��ѯ
	
	public List<Order> findAllOrder();//��ѯ���ж���
	public List<Order> findFailOrder();//��ѯ����ʧ����Ϣ
	List<Order> findSell(User user);//��������ɵĶ����и���״̬��ѯ
	List<Order> findSell(User user,String state);//��������ɵĶ����и���״̬��ѯ
}
