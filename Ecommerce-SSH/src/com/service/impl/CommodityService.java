package com.service.impl;

import java.util.List;


//import org.springframework.stereotype.Service;





import com.dao.ICommodityDAO;
import com.po.Commodity;
import com.po.User;
import com.service.ICommodityService;
import com.tools.extractList;

//@Service 
public class CommodityService implements ICommodityService{
	
	private ICommodityDAO commodityDAO;

	public ICommodityDAO getCommodityDAO() {
		return commodityDAO;
	}


	public void setCommodityDAO(ICommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}


	public CommodityService()
	{
		System.out.println("creatCommodityService");
	}


	@Override
	public boolean addCommodity(Commodity commodity) {
		return commodityDAO.save(commodity);
	}
	@Override
	public boolean deleteCommodity(int id) {
		return commodityDAO.delete(id);
	}
	@Override
	public Commodity findCommodity(int id) {
		return commodityDAO.find(id);
	}
	@Override
	public List<Commodity> findAllCommodity() {
		return commodityDAO.findAll();
	}
	@Override
	public List<Commodity> findCommodity(Commodity commodity) {
		extractList list = new extractList();
		List<Commodity> u,u2,u3;
		System.out.println("name:"+commodity.getName()+"*"+"property:"+commodity.getProperty());
		if(commodity == null || commodity.getName() == "")
		{
			u = commodityDAO.findAll();
		}
		else{
			u = commodityDAO.findAllbyName(commodity.getName());
		}
		if(commodity == null || commodity.getProperty().equals(""))
		{
			u2 = commodityDAO.findAll();
			
		}else{
			u2 = commodityDAO.findAllbyProperty(commodity.getProperty());
		}	
		if(commodity == null || commodity.getState() == "")
		{
			u3 = commodityDAO.findAll();
		}else{
			u3 = commodityDAO.waitToPassCommodity(commodity.getState());
		}
		//ºÏ²¢
		@SuppressWarnings("static-access")
		List<Commodity> result = list.getExtractlistCommodity(u, u2);
		@SuppressWarnings("static-access")
		List<Commodity> result2 = list.getExtractlistCommodity(result, u3);
		/*if(result != null)
		{
			for(int i = 0; i < result.size(); i++)
			{
				System.out.println("result:id:"+result.get(i).getId()+" name:"+result.get(i).getName());
			}
		}*/
		return result2;
	}
	@Override
	public List<Commodity> waitToPassCommodity(String state) {
		return commodityDAO.waitToPassCommodity(state);
	}
	@Override
	public boolean updateCommodity(Commodity commodity,String state) {
		commodity.setState(state);
		return commodityDAO.update(commodity);
	}
	@Override
	public boolean updateCommodity(Commodity commodity) {
		return commodityDAO.update(commodity);
	}


	@Override
	public List<Commodity> findCommodity(User user) {
		return commodityDAO.findbyUser(user);

	}


}
