package com.service.iml;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CommodityDAO;
import com.po.Commodity;
import com.service.ICommodityService;

@Service @Transactional
public class CommodityService implements ICommodityService{
	
	@Resource SessionFactory factory;
	private CommodityDAO commodityDAO;
	public CommodityDAO getCommodityDAO() {
		return commodityDAO;
	}
	public CommodityService()
	{
		System.out.println("creatCommodityService");
	}
	public void setCommodityDAO(CommodityDAO commodityDAO) {
		System.out.println("-----setCommodityDAO-----");
		this.commodityDAO = commodityDAO;
	}
	public boolean save(Commodity commodity) {
		System.out.println("-----commodityService.save()。in-----");
		try{
			System.out.println("userid:"+commodity.getUser().getId()+" userName:"+commodity.getUser().getName());			
			factory.getCurrentSession().persist(commodity);
			System.out.println("-----commodityService.save()。out-----");
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public List<Commodity> findAll() {
		return commodityDAO.findAll();
	}

	public Commodity find(int id){
		return (Commodity)factory.getCurrentSession().get(Commodity.class, id);
	}
	@Override
	public boolean update(Commodity commodity) {//修改商品信息
		try{
			factory.getCurrentSession().merge(commodity);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}


}
