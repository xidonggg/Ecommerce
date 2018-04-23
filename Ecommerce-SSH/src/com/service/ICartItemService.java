package com.service;

import java.util.List;

import com.po.CartItem;
import com.po.User;


public interface ICartItemService {
	public List<CartItem> find(User user);//��ʾĳ�û����й����嵥
	public List<CartItem> findDetial(User user);//��ʾĳ�û����й����嵥
	public boolean addCartItem(CartItem cartItem);//��ӹ��ﳵ
	public boolean deleteCartItem(int id);//ɾ�����ﳵ
	public CartItem find(int id);
	public double count(User user,int score);
	//public boolean upNum(int id);//��������
	//public boolean downNum(int id);//��������
	//public boolean clearCartItem();//��չ��ﳵ
}
