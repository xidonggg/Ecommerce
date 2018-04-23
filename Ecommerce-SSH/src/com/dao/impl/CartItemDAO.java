package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICartItemDAO;
import com.po.CartItem;
import com.po.User;

//@Repository
public class CartItemDAO  extends HibernateDaoSupport implements ICartItemDAO{
	@Resource SessionFactory factory;
	public CartItemDAO()
	{
		System.out.println("creat  CartItemDAO");
	}
	@Override
	public List<CartItem> findAll(User user) {
		String hql="from CartItem c where c.user = ?";
		System.out.println("-----CartItemDAO.show----");
		@SuppressWarnings("unchecked")
		List<CartItem>cartItems = this.getHibernateTemplate().find(hql,user);
		if(!cartItems.isEmpty())
			return cartItems;
		else
		{
			System.out.println("-----CartItemDAO.show.list=empty----");
			return null;
		}
	}

	@Override
	@Transactional
	public boolean save(CartItem cartItem) {
		try{
			this.getHibernateTemplate().persist(cartItem);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public CartItem find(int id) {
		String hql = "from CartItem c where c.id=?";
		@SuppressWarnings("unchecked")
		List<CartItem> list = this.getHibernateTemplate().find(hql, id);
		if(list != null)
			return list.get(0);
		return null;
	}

	@Override
	@Transactional
	public boolean update(CartItem cartItem) {
		try{
			this.getHibernateTemplate().merge(cartItem);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try{
			this.getHibernateTemplate().delete(find(id));
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<CartItem> findAll() {
		String hql="from CartItem o ";
		@SuppressWarnings("unchecked")
		List<CartItem>list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
