package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.Admin;


public class AdminDAO extends HibernateDaoSupport implements IAdminDAO{

	public AdminDAO()
	{
		System.out.println("creatAdminDAO");
	}
	@Override
	public Admin check(Admin admin) {
		if(admin != null)
		{
			String hql="from Admin a where a.name=? and a.password=?";
			System.out.println("-----adminDAO.check----");
			@SuppressWarnings("unchecked")
			List<Admin>list = this.getHibernateTemplate().find(hql,new String[]{admin.getName(),admin.getPassword()});
			if(!list.isEmpty())
				return list.get(0);
			else
			{
				System.out.println("-----adminDAO.check.list=empty----");
				return null;
			}
		}
		else
		{
			System.out.println("-----adminDAO.check.admin==null---");
			return null;
		}
	}

	@Override
	public boolean checkName(String adminName) {
		if(adminName != null)
		{
			String hql="from Admin a where a.name=?";
			System.out.println("-----adminDAO.checkName----");
			@SuppressWarnings("unchecked")
			List<Admin>list = this.getHibernateTemplate().find(hql,adminName);
			if(!list.isEmpty())
			{
				System.out.println("-----adminDAO.checkName.list!=empty----");
				return false;
			}
			else
			{
				System.out.println("-----adminDAO.checkName.list=empty----");
				return true;
			}
		}
		else
		{
			System.out.println("-----adminDAO.checkName.user==null---");
			return false;
		}
	}

}
