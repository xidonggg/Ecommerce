package com.dao;

import java.util.List;

import com.po.CartItem;
import com.po.User;

public interface ICartItemDAO {
	public boolean save(CartItem cartItem);
	public CartItem find(int id);
	public boolean update(CartItem cartItem);
	public boolean delete(int id);
	public List<CartItem> findAll();
	public List<CartItem> findAll(User user);//显示购物车中所有商品
}
