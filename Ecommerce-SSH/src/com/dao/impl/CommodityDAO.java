package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICommodityDAO;
import com.po.Commodity;
import com.po.User;

//@Repository
public class CommodityDAO extends HibernateDaoSupport implements ICommodityDAO{
	@Resource SessionFactory factory;
	public CommodityDAO()
	{
		System.out.println("creat  Commodity");
	}

	@Override
	public List<Commodity> findAll() {
		String hql="from Commodity c";
		System.out.println("-----CommodityDAO.show----");
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql);
		return commodities;
	}

	@Override
	@Transactional
	public boolean save(Commodity commodity) {
		try{
			this.getHibernateTemplate().persist(commodity);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Commodity find(int id) {
		String hql = "from Commodity c where c.id=?";
		@SuppressWarnings("unchecked")
		List<Commodity> list = this.getHibernateTemplate().find(hql, id);
		if(list != null)
			return list.get(0);
		return null;
	}

	@Override
	@Transactional
	public boolean update(Commodity commodity) {
		try{
			this.getHibernateTemplate().merge(commodity);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(int commodityid) {
		try{
			this.getHibernateTemplate().delete(find(commodityid));
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Commodity> find(User user) {
		String hql="from Commodity c where c.user=?";
		System.out.println("-----CommodityDAO.show----");
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql,String.valueOf(user.getId()));
		return commodities;
	}

	@Override
	public List<Commodity> waitToPassCommodity(String state) {
		String hql="from Commodity c where c.state=?";
		System.out.println("-----CommodityDAO.show----");
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql,state);
		return commodities;
	}

	@Override
	public List<Commodity> findAllbyName(String name) {
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find("from Commodity c where c.name like ?","%"+name+"%");
		return commodities;
	}

	@Override
	public List<Commodity> findAllbyProperty(String property) {
		String hql="from Commodity c where c.property=?";
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql,property);
		return commodities;
	}

	@Override
	public List<Commodity> findbyUser(User user) {
		String hql="from Commodity c where c.user=?";
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql,user);
		return commodities;
	}

}
