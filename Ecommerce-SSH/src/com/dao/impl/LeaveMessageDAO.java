package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ILeaveMessageDAO;
import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;

//@Repository
public class LeaveMessageDAO  extends HibernateDaoSupport implements ILeaveMessageDAO{
	@Resource SessionFactory factory;
	public LeaveMessageDAO(){}
	@Override
	@Transactional
	public boolean save(LeaveMessage leaveMessage) {
		try{
			this.getHibernateTemplate().save(leaveMessage);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(LeaveMessage leaveMessage) {
		try{
			this.getHibernateTemplate().merge(leaveMessage);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public LeaveMessage find(int id) {
		String hql = "from LeaveMessage o where o.id=?";
		@SuppressWarnings("unchecked")
		List<LeaveMessage> list = this.getHibernateTemplate().find(hql, id);
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
	public List<LeaveMessage> findAll() {
		String hql="from LeaveMessage o ";
		@SuppressWarnings("unchecked")
		List<LeaveMessage>list = this.getHibernateTemplate().find(hql);
		return list;
	}
	@Override
	public List<LeaveMessage> findAll(Commodity commodity) {
		String hql="from LeaveMessage o where o.commodity=?";
		@SuppressWarnings("unchecked")
		List<LeaveMessage>list = this.getHibernateTemplate().find(hql,commodity);
		return list;
	}
	@Override
	public List<LeaveMessage> findAll(User user) {
		String hql="from LeaveMessage o where o.user=?";
		@SuppressWarnings("unchecked")
		List<LeaveMessage>list = this.getHibernateTemplate().find(hql,user);
		return list;
	}

}
