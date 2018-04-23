package com.dao;

import java.util.List;

import com.po.Admin;

public interface IAdminDAO {
	public boolean save(Admin admin);
	public boolean update(Admin admin);
	public Admin find(int id);
	public boolean delete(int id);
	public List<Admin> findAll();
	public Admin check(Admin admin);//用户登录检查
	public boolean checkName(String adminName);//检查用户名是否已被注册
}
