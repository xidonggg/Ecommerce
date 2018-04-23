package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.User;

public class UserDAO extends HibernateDaoSupport implements IUserDAO{
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
}