package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.Commodity;

public class CommodityDAO extends HibernateDaoSupport implements ICommodityDAO{
	public CommodityDAO()
	{
		System.out.println("-----creatCommodity-----");
	}

	@Override
	public List<Commodity> findAll() {
		String hql="from Commodity c";
		System.out.println("-----CommodityDAO.show----");
		@SuppressWarnings("unchecked")
		List<Commodity>commodities = this.getHibernateTemplate().find(hql);
		if(!commodities.isEmpty())
			return commodities;
		else
		{
			System.out.println("-----CommodityDAO.show.list=empty----");
			return null;
		}
	}

}
