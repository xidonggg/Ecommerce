package com.service.iml;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.Order;
import com.service.IOrderService;

@Service @Transactional
public class OrderService implements IOrderService{
	@Resource SessionFactory factory;

	@Override
	public boolean save(Order order) {
		factory.getCurrentSession().persist(order);
		return false;
	}

	@Override
	public Order find(int orderid) {
		return (Order)factory.getCurrentSession().get(Order.class, orderid);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Order> findAll() {
		return factory.getCurrentSession().createQuery("from order ").list();
	}

}
