package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.CartItem;
import com.po.User;

public class CartItemDAO  extends HibernateDaoSupport implements ICartItemDAO{

	@Override
	public List<CartItem> showCartItems(User user) {
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

}
