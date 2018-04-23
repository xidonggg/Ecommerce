package com.service;

import java.util.List;

import com.po.Commodity;
import com.po.User;

public interface ICommodityService {
	public boolean addCommodity(Commodity commodity);//�����Ʒ
	public boolean updateCommodity(Commodity commodity);//�޸���Ʒ����
	public boolean deleteCommodity(int id);//ɾ����Ʒ
	public Commodity findCommodity(int id);//����ĳ��Ʒ
	public List<Commodity> findAllCommodity();//����������Ʒ
	public List<Commodity> findCommodity(Commodity commodity);//������ѯ��Ʒ
	public List<Commodity> findCommodity(User user);//���ݷ����߲�����Ʒ
	
	public List<Commodity> waitToPassCommodity(String state);//�������Ʒ
	public boolean updateCommodity(Commodity commodity,String state);//���ͨ��
}
