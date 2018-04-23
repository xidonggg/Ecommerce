package com.service.iml;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.OrderItem;
import com.service.IOrderItemService;

@Service @Transactional
public class OrderItemService implements IOrderItemService{
	@Resource SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<OrderItem> findAll() {
		return factory.getCurrentSession().createQuery("from orderItem ").list();
	}
	
	@Override
	public boolean save(OrderItem orderItem) {
		try{
			factory.getCurrentSession().persist(orderItem);
			return true;
		}catch(Exception e)
		{	
			return false;
		}
	
	}
	@Override
	public boolean save(List<OrderItem> orderItemlist) {
		try{
			for(int i = 0; i < orderItemlist.size(); i++)
			{
				factory.getCurrentSession().persist(orderItemlist.get(i));
			}
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
}
