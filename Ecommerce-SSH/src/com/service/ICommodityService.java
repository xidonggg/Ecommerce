package com.service;

import java.util.List;

import com.po.Commodity;
import com.po.User;

public interface ICommodityService {
	public boolean addCommodity(Commodity commodity);//添加商品
	public boolean updateCommodity(Commodity commodity);//修改商品属性
	public boolean deleteCommodity(int id);//删除商品
	public Commodity findCommodity(int id);//查找某商品
	public List<Commodity> findAllCommodity();//查找所有商品
	public List<Commodity> findCommodity(Commodity commodity);//条件查询商品
	public List<Commodity> findCommodity(User user);//根据发布者查找商品
	
	public List<Commodity> waitToPassCommodity(String state);//待审核商品
	public boolean updateCommodity(Commodity commodity,String state);//审核通过
}
