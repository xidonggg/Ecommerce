package com.service.iml;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDAO;
import com.po.Admin;
import com.service.IAdminService;

@Service @Transactional
public class AdminService implements IAdminService{

	@Resource SessionFactory factory;
	private AdminDAO adminDAO;
	public AdminService()
	{
		System.out.println("creatAdminService");
	}
	
	public boolean save(Admin admin) {
		System.out.println("-----adminService.save()¡£in-----");
		try{
			System.out.println("userid:"+admin.getId()+" userName:"+admin.getName());			
			factory.getCurrentSession().persist(admin);
			System.out.println("-----adminService.save()¡£out-----");
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public boolean checkName(String adminName) {
		if(this.adminDAO.checkName(adminName))
			return true;
		else
			return false;
	}
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}


}
