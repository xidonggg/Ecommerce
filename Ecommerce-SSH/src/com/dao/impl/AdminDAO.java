package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IAdminDAO;
import com.po.Admin;

//@Repository
public class AdminDAO extends HibernateDaoSupport implements IAdminDAO{
	@Resource SessionFactory factory;
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
	@Override
	@Transactional
	public boolean save(Admin admin) {
		try{
			this.getHibernateTemplate().persist(admin);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	@Transactional
	public boolean update(Admin admin) {
		try{
			this.getHibernateTemplate().merge(admin);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public Admin find(int id) {
		String hql = "from Admin c where c.id=?";
		@SuppressWarnings("unchecked")
		List<Admin> list = this.getHibernateTemplate().find(hql,id);
		if(list != null)
			return list.get(0);
		return null;
	}
	@Override
	@Transactional
	public boolean delete(int id) {
		try{
			this.getHibernateTemplate().delete(find(id));
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public List<Admin> findAll() {
		String hql="from Admin o ";
		@SuppressWarnings("unchecked")
		List<Admin>list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
