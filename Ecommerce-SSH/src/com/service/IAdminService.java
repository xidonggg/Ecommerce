package com.service;


import com.po.Admin;

public interface IAdminService {
	public boolean registerAdmin(Admin admin);//注册
	public boolean updateAdmin(Admin admin);//修改信息
	public boolean deleteAdmin(int id);//删除管理员
	public String getMsg();
	public Admin loginAdmin(Admin admin);//登陆
	public Admin find(int id);//查找

	//管理留言信息
	//public List<>
	
}
