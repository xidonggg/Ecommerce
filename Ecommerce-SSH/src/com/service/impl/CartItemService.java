package com.service.impl;

import java.util.List;


//import org.springframework.stereotype.Service;

import com.dao.ICartItemDAO;
import com.dao.ICommodityDAO;
import com.po.CartItem;
import com.po.Commodity;
import com.po.User;
import com.service.ICartItemService;

//@Service 
public class CartItemService implements ICartItemService{
	
	private ICartItemDAO cartItemDAO;
	private ICommodityDAO commodityDAO;
	public ICommodityDAO getCommodityDAO() {
		return commodityDAO;
	}
	public void setCommodityDAO(ICommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}
	public ICartItemDAO getCartItemDAO() {
		return cartItemDAO;
	}
	public void setCartItemDAO(ICartItemDAO cartItemDAO) {
		this.cartItemDAO = cartItemDAO;
	}

	@Override
	public List<CartItem> find(User user) {
		return cartItemDAO.findAll(user);
	}
	@Override
	public boolean addCartItem(CartItem cartItem) {
		return cartItemDAO.save(cartItem);
	}
	@Override
	public boolean deleteCartItem(int id) {
		return cartItemDAO.delete(id);
	}
	@Override
	public CartItem find(int id) {
		return cartItemDAO.find(id);
	}
	@Override
	public double count(User user, int score) {//¼ÆËã×Ü½ð¶î
		double c = 0;
		List<CartItem> cartItems = find(user);
		if(cartItems == null)
		{
			return 0;
		}
		else{
			for(int i = 0; i < cartItems.size(); i++)
			{
				Commodity comm = commodityDAO.find(cartItems.get(i).getCommodity().getId());
				c += comm.getPrice() * cartItems.get(i).getNeedNum();
			}
			if((c - score*0.01) >= 0)
				c = c - score*0.01;
			else
				return -1;
			return c;
		}
	}
	@Override
	public List<CartItem> findDetial(User user) {
		List<CartItem>cartItems = cartItemDAO.findAll(user);
		if(cartItems != null)
		{
			for(int i = 0; i < cartItems.size(); i++)
			{
				Commodity c = commodityDAO.find(cartItems.get(i).getCommodity().getId());
				cartItems.get(i).setCommodity(c);
			}
		}
		return cartItems;
	}
}
