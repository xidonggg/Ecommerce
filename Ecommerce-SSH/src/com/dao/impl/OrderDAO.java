package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IOrderDAO;
import com.po.Order;
import com.po.User;

//@Repository
public class OrderDAO extends HibernateDaoSupport implements IOrderDAO{
	@Resource SessionFactory factory;
	public OrderDAO(){
		System.out.println("create OrderDAO");
	}
	@Override
	@Transactional
	public boolean save(Order order) {
		try{
			this.getHibernateTemplate().persist(order);
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	@Transactional
	public boolean delete(int orderid) {
		try{
			this.getHibernateTemplate().delete(find(orderid));
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	@Transactional
	public boolean update(Order order) {
		try{
			this.getHibernateTemplate().merge(order);
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	public Order find(int orderid) {
		String hql = "from Order o where o.id=?";
		@SuppressWarnings("unchecked")
		List<Order> list = this.getHibernateTemplate().find(hql, orderid);
		if(list != null)
			return list.get(0);
		return null;
	}

	@Override
	public List<Order> findAll() {
		String hql="from Order o ";
		@SuppressWarnings("unchecked")
		List<Order>list = this.getHibernateTemplate().find(hql);
		return list;
	}
	@Override
	public List<Order> find(User user) {
		String hql="from Order o where o.user=?";
		@SuppressWarnings("unchecked")
		List<Order>list = this.getHibernateTemplate().find(hql,user);
		return list;
	}
	@Override
	public List<Order> findbyState(String state) {
		String hql="from Order o where o.state=?";
		@SuppressWarnings("unchecked")
		List<Order>list = this.getHibernateTemplate().find(hql,state);
		return list;
	}
	@Override
	public List<Order> findbyAddress(String address) {
		@SuppressWarnings("unchecked")
		List<Order>list = this.getHibernateTemplate().find("from Order u where u.address like ?","%"+address+"%");
		return list;
	}
}
