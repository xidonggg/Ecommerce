package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IUserDAO;
import com.po.User;

//@Repository
public class UserDAO extends HibernateDaoSupport implements IUserDAO{
	@Resource SessionFactory factory;
	
	public UserDAO()
	{
		System.out.println("create UserDAO");
	}
	public User check(User user)
	{//登陆检查
		if(user != null)
		{
			String hql="from User u where u.name=? and u.password=?";
			System.out.println("-----UserDAO.check----");
			@SuppressWarnings("unchecked")
			List<User>list = this.getHibernateTemplate().find(hql,new String[]{user.getName(),user.getPassword()});
			if(!list.isEmpty())
				return list.get(0);
			else
			{
				System.out.println("-----UserDAO.check.list=empty----");
				return null;
			}
		}
		else
		{
			System.out.println("-----UserDAO.check.user==null---");
			return null;
		}
	}
	public boolean checkName(String userName)
	{//检验用户名是否已注册
		if(userName != null)
		{
			String hql="from User u where u.name=?";
			System.out.println("-----UserDAO.checkName----");
			@SuppressWarnings("unchecked")
			List<User>list = this.getHibernateTemplate().find(hql,userName);
			if(!list.isEmpty())
			{
				System.out.println("-----UserDAO.checkName.list!=empty----");
				return false;
			}
			else
			{
				System.out.println("-----UserDAO.checkName.list=empty----");
				return true;
			}
		}
		else
		{
			System.out.println("-----UserDAO.checkName.user==null---");
			return false;
		}
	}
	@Override
	@Transactional
	public boolean save(User user) {
		try{
			System.out.println("persist:id:"+user.getId()+"name:"+user.getName());
			//this.getHibernateTemplate().persist(user);
			factory.getCurrentSession().persist(user);
			System.out.println("success");
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}
	@Override
	@Transactional
	public boolean update(User user) {
		try{
			this.getHibernateTemplate().merge(user);
			return true;
		}catch(Exception e)
		{
			return false;
		}	
	}
	@Override
	public User find(int id) {
		/*
		System.out.println("iiiiiiiid:"+id);
		//String hql = "from User c where c.id=?";
		@SuppressWarnings("unchecked")
		List<User>list = this.getHibernateTemplate().find("from User u where u.id=?",id);
		if(list != null)
			return list.get(0);
		return null;*/
		System.out.println("iiiiiiiid:"+id);
		String hql = "from User c where c.id=?";
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().find(hql, id);
		if(list != null)
			return list.get(0);
		return null;
	}
	@Override
	@Transactional
	public boolean delete(User user) {
		try{
			System.out.println("deleteDAOid:"+user.getId());
			this.getHibernateTemplate().delete(find(user.getId()));
			return true;
		}catch(Exception e)
		{
			return false;
		}	

	}
	@Override
	public List<User> findAll() {
		System.out.println("----UserDAO.findALLbefore----");
		String hql="from User u ";
		@SuppressWarnings("unchecked")
		List<User>list = this.getHibernateTemplate().find(hql);
		System.out.println("----UserDAO.findALL----");
		return list;
	}
	@Override
	public List<User> findAllbyAddress(String address) {
		//String hql="from User u where u.name=? and u.address like '%test%' ";
		@SuppressWarnings("unchecked")
		List<User>list = this.getHibernateTemplate().find("from User u where u.address like ?","%"+address+"%");
		return list;
	}
	@Override
	public List<User> findAllbyName(String name) {
		System.out.println("里面"+name);
		String hql="from User u where u.name=?";
		System.out.println("hibernate是空的吗"+this.getHibernateTemplate());
		@SuppressWarnings("unchecked")
		List<User>list = this.getHibernateTemplate().find(hql,name);
		if(list != null)
			return list;
		else
			return null;
	}
}