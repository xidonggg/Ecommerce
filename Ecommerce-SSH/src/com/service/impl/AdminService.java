package com.service.impl;


import com.dao.IAdminDAO;
import com.po.Admin;
import com.service.IAdminService;

//@Service
public class AdminService implements IAdminService{

	private IAdminDAO adminDAO;
	private String msg = "";
	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public AdminService()
	{
		System.out.println("creatAdminService");
	}
	
	
	public IAdminDAO getAdminDAO() {
		return adminDAO;
	}
	public void setAdminDAO(IAdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public boolean registerAdmin(Admin admin) {
		System.out.println("userid:"+admin.getId()+" userName:"+admin.getName());	
		if(adminDAO.checkName(admin.getName()))//检查用户名是否已被使用
		{
			adminDAO.save(admin);
			return true;
		}	
		else{
			return false;
		}

	}

	@Override
	public boolean updateAdmin(Admin admin) {
		if(adminDAO.checkName(admin.getName()))//检查用户名是否已被使用
		{
			adminDAO.update(admin);
			return true;
		}	
		else{
			return false;
		}
	}

	@Override
	public boolean deleteAdmin(int id) {
		return adminDAO.delete(id);
	}


	@Override
	public Admin loginAdmin(Admin admin) {
		return adminDAO.check(admin);
	}


	@Override
	public Admin find(int id) {
		return adminDAO.find(id);
	}


}
