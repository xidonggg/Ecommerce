package com.service;

import java.util.List;

import com.po.CartItem;
import com.po.User;


public interface ICartItemService {
	public List<CartItem> find(User user);//显示某用户所有购物清单
	public List<CartItem> findDetial(User user);//显示某用户所有购物清单
	public boolean addCartItem(CartItem cartItem);//添加购物车
	public boolean deleteCartItem(int id);//删除购物车
	public CartItem find(int id);
	public double count(User user,int score);
	//public boolean upNum(int id);//增加数量
	//public boolean downNum(int id);//减少数量
	//public boolean clearCartItem();//清空购物车
}
