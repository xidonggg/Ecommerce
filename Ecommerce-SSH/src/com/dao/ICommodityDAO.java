package com.dao;

import java.util.List;

import com.po.Commodity;
import com.po.User;

public interface ICommodityDAO {
	public boolean save(Commodity commodity);//������Ʒ��Ϣ
	public Commodity find(int id);//��id������Ʒ
	public boolean update(Commodity commodity);//�޸���Ʒ��Ϣ
	public boolean delete(int commodityid);
	public List<Commodity> findAll();//��ʾ������Ʒ
	public List<Commodity> find(User user);//��ѯĳ�˷�������Ʒ
	public List<Commodity> waitToPassCommodity(String state);//����״̬��ѯ
	public List<Commodity> findAllbyName(String name);
	public List<Commodity> findAllbyProperty(String property);
	public List<Commodity> findbyUser(User user);//����user������Ʒ
}
