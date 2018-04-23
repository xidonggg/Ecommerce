package com.service.iml;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICartItemDAO;
import com.po.CartItem;
import com.po.User;
import com.service.ICartItemService;

@Service @Transactional
public class CartItemService implements ICartItemService{
	@Resource SessionFactory factory;
	private ICartItemDAO cartItemDAO;
	public ICartItemDAO getCartItemDAO() {
		return cartItemDAO;
	}
	public void setCartItemDAO(ICartItemDAO cartItemDAO) {
		this.cartItemDAO = cartItemDAO;
	}
	@Override
	public List<CartItem> findAll(User user) {
		return cartItemDAO.findAll(user);
	}
	@Override
	public boolean save(CartItem cartItem) {
		try{
			factory.getCurrentSession().persist(cartItem);
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
	@Override
	public boolean delete(int id) {
		try{
			factory.getCurrentSession().delete(factory.getCurrentSession().get(CartItem.class, id));
			return true;
		}catch(Exception e)
		{
			return false;
		}

	}
	public CartItem find(int id)
	{
		return (CartItem)factory.getCurrentSession().get(CartItem.class, id);
	}
}
