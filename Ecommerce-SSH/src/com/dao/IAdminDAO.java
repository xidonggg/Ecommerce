package com.dao;

import java.util.List;

import com.po.Admin;

public interface IAdminDAO {
	public boolean save(Admin admin);
	public boolean update(Admin admin);
	public Admin find(int id);
	public boolean delete(int id);
	public List<Admin> findAll();
	public Admin check(Admin admin);//�û���¼���
	public boolean checkName(String adminName);//����û����Ƿ��ѱ�ע��
}
