package com.dao;

import java.util.List;

import com.po.User;

public interface IUserDAO {
	public boolean save(User user);//����
	public boolean update(User user);//����
	public User find(int id);//��ѯ
	public boolean delete(User user);//ɾ��
	public List<User>findAll();//�����û�
	public boolean checkName(String userName);//��������Ƿ��Ѿ�����
	public User check(User user);//�û���¼���
	public List<User>findAllbyAddress(String address);//����ַ��ѯ
	public List<User>findAllbyName(String name);//��������ѯ
}
