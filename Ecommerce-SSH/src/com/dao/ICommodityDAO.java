package com.dao;

import java.util.List;

import com.po.Commodity;
import com.po.User;

public interface ICommodityDAO {
	public boolean save(Commodity commodity);//保存商品信息
	public Commodity find(int id);//按id查找商品
	public boolean update(Commodity commodity);//修改商品信息
	public boolean delete(int commodityid);
	public List<Commodity> findAll();//显示所有商品
	public List<Commodity> find(User user);//查询某人发布的商品
	public List<Commodity> waitToPassCommodity(String state);//根据状态查询
	public List<Commodity> findAllbyName(String name);
	public List<Commodity> findAllbyProperty(String property);
	public List<Commodity> findbyUser(User user);//根据user查找商品
}
