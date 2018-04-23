package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IOrderItemDAO;
import com.po.Order;
import com.po.OrderItem;

//@Repository
public class OrderItemDAO extends HibernateDaoSupport implements IOrderItemDAO{
	@Resource SessionFactory factory;

	public OrderItemDAO(){
		System.out.println("creat  OrderItemDAO");
	}
	@Override
	@Transactional
	public boolean save(OrderItem orderItem) {
		try{
			this.getHibernateTemplate().persist(orderItem);
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	@Transactional
	public boolean update(OrderItem orderItem) {
		try{
			this.getHibernateTemplate().merge(orderItem);
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	public OrderItem find(int orderItemid) {
		return (OrderItem)factory.getCurrentSession().get(OrderItem.class, orderItemid);
	}

	@Override
	@Transactional
	public boolean delete(int orderItemid) {
		try{
			this.getHibernateTemplate().delete(find(orderItemid));
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	public List<OrderItem> findAll() {
		String hql="from OrderItem o ";
		@SuppressWarnings("unchecked")
		List<OrderItem>list = this.getHibernateTemplate().find(hql);
		return list;
	}
	@Override
	public List<OrderItem> findbyOrder(Order order) {
		String hql="from OrderItem c where c.order = ?";
		@SuppressWarnings("unchecked")
		List<OrderItem>orderItems = this.getHibernateTemplate().find(hql,order);
		if(!orderItems.isEmpty())
			return orderItems;
		else
		{
			return null;
		}
	}
	/*@Override
	public List<OrderItem> findbyOrderAState(Order order, String state) {
		String hql="from OrderItem c where c.order = ? and c.state=?";
		@SuppressWarnings("unchecked")
		List<OrderItem>orderItems = this.getHibernateTemplate().find(hql,new Object[]{order,state});
		if(!orderItems.isEmpty())
			return orderItems;
		else
		{
			return null;
		}
	}*/


}
