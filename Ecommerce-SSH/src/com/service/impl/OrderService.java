package com.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




import com.dao.ICommodityDAO;
import com.dao.IOrderDAO;
import com.dao.IOrderItemDAO;
import com.dao.IUserDAO;
import com.po.Commodity;
import com.po.Order;
import com.po.OrderItem;
import com.po.User;
import com.service.IOrderService;
import com.tools.extractList;

//@Service
public class OrderService implements IOrderService{
	//@Resource SessionFactory factory;
	private IOrderDAO orderDAO;
	private IOrderItemDAO orderItemDAO;
	private IUserDAO userDAO;
	private ICommodityDAO commodityDAO;
	public OrderService()
	{
		
	}
	@Override
	public boolean addOrder(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public boolean updateOrder(Order order,String state) {
		Order o = find(order.getId());
		System.out.println("******************order.naem"+order.getName());
		o.setState(state);
		return orderDAO.update(o);
	}

	@Override
	public boolean addOrderItem(OrderItem orderItem) {
		return orderItemDAO.save(orderItem);
	}

	@Override
	public List<Order> findAllOrder() {
		return orderDAO.findAll();	
	}

	@Override
	public List<Order> findFailOrder() {
		List<Order> list = findAllOrder();
		List<Order> list2 = new ArrayList<Order>();
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getState().equals("失败"))
				list2.add(list.get(i));
		}
		return list2;
	}
	//@Override
	public List<Order> findConcreteOrder() {
		List<Order> list = orderDAO.findAll();	
		for(int i = 0; i < list.size(); i++)
		{
			//System.out.println("");
			if(list.get(i).getUser() != null)
			{
				if(list.get(i).getUser().getId() != 0)
				{
					int id = list.get(i).getUser().getId();
					User u = userDAO.find(id);
					list.get(i).setUser(u);
				}

			}
		}
		return list;
	}
	public List<Order> findConcreteOrder(List<Order> orders){//往order中完善User和orderItem信息
		if(orders != null)
			for(int i = 0; i < orders.size(); i++)
			{
				if(orders.get(i).getUser() != null)
				{//完善用户信息
					if(orders.get(i).getUser().getId() != 0)
					{
						User u = userDAO.find(orders.get(i).getUser().getId());
						System.out.println("找到user：name:"+u.getName());
						orders.get(i).setUser(u);
					}
				}
				//完善orderItem信息
				List<OrderItem> om = orderItemDAO.findbyOrder(orders.get(i));
				om = findConcreteOrderItem(om);//完善ccommodity信息
				Set<OrderItem> sm = new HashSet<OrderItem>();
				if(om != null)
				{
					for(int j = 0; j < om.size(); j++)
					{
						sm.add(om.get(j));
					}
					if(sm != null)
					{
						java.util.Iterator<OrderItem> it = sm.iterator();
						while(it.hasNext())
						{
							
							System.out.println("oooooooooooooid:"+it.next().getId());
						}
					}
					orders.get(i).setOrderItemlist(sm);	
				}				
				
			}
		return orders;
	}

	public List<OrderItem> findConcreteOrderItem(List<OrderItem> orderItems){//往orderItem中完善commodity信息
		if(orderItems != null)
			for(int i = 0; i < orderItems.size(); i++)
			{
				//Order o = orderDAO.find(orderItems.get(i).getOrder().getId());
				//System.out.println("找到order：name:"+o.getName());
				Commodity c = commodityDAO.find(orderItems.get(i).getCommodity().getId());
				orderItems.get(i).setCommodity(c);
				
			}
		return orderItems;
	}
	@Override
	public List<Order> findAllOrder(Order order) {
		extractList list = new extractList();
		List<Order> u = null,u2,u3;
		System.out.println("u0:id:"+order.getId()+"name:"+order.getUser().getName()+"*"+"state:"+order.getState()+"address:"+order.getAddress());
		List<User> users = userDAO.findAllbyName(order.getUser().getName());
		if(order.getUser().getName().equals(""))
		{
			u = orderDAO.findAll();
			/*if(users != null && users.size() != 0)
			{//放入user
				if(u != null)
					for(int i = 0; i < u.size(); i++)
					{
						u.get(i).setUser(users.get(0));
					}
			}*/
		}
		else{
			if(users != null && users.size() != 0)
			{
				u = orderDAO.find(users.get(0));
				/*if(u != null)
					for(int i = 0; i < u.size(); i++)
					{
						u.get(i).setUser(users.get(0));
					}*/
			}
		}
		if(order.getAddress().equals(""))
		{
			u2 = orderDAO.findAll();
			
		}else{
			u2 = orderDAO.findbyAddress(order.getAddress());
		}	
		if(order.getState().equals(""))
		{
			System.out.println("state是空的");
			u3 = orderDAO.findAll();
		}else{
			System.out.println(order.getState());
			u3 = orderDAO.findbyState(order.getState());
		}
		
		//合并
		@SuppressWarnings("static-access")
		List<Order> result = list.getExtractlistOrder(u, u2);
		@SuppressWarnings("static-access")
		List<Order> result2 = list.getExtractlistOrder(result, u3);
		
		result2 = findConcreteOrder(result2);
		System.out.println("********************************************");
		if(result2 != null)
		{
			for(int i = 0; i < result2.size(); i++)
			{
				System.out.println("result2:id:"+result.get(i).getId()+" name:");
				if(result.get(i).getUser() != null)
					System.out.println(result.get(i).getUser().getName());
			}
		}
		
		return result2;
	}
	@Override
	public List<OrderItem> findAllOrderItem(Order order) {//根据order查找订单项
		System.out.println("!!!!!!!!!!!!!!!!!!orderid"+order.getId());
		List<OrderItem> list = orderItemDAO.findbyOrder(order);
		list = findConcreteOrderItem(list);
		/*if(list != null)
			for(int i = 0; i < list.size(); i++)
			{
				System.out.println("listZhong id:"+list.get(i).getCommodity().getName());
			}*/
		return list;
	}
	@Override
	public List<OrderItem> findSellItem(User user) {
		List<OrderItem> list = orderItemDAO.findAll();
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		for(int i = 0; i < list.size(); i++)
		{
			Commodity c = commodityDAO.find(list.get(i).getCommodity().getId());
			if(c.getUser().getId() == user.getId())
			{
				Order o = find(list.get(i).getOrder().getId());
				list.get(i).setOrder(o);
				list.get(i).setCommodity(c);
				list2.add(list.get(i));
			}
			
		}
		return list2;
	}
	@Override
	public List<Order> findSell(User user) {
		List<OrderItem> list = orderItemDAO.findAll();
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		for(int i = 0; i < list.size(); i++)
		{
			Commodity c = commodityDAO.find(list.get(i).getCommodity().getId());
			if(c.getUser().getId() == user.getId())
			{
				Order o = find(list.get(i).getOrder().getId());
				list.get(i).setOrder(o);
				list.get(i).setCommodity(c);
				list2.add(list.get(i));
			}
			
		}
		List<Order> lo = new ArrayList<Order>();
		if(list2 != null)
		{
			for(int i = 0; i < list2.size(); i++)
			{
				boolean flag = true;
				for(int j = 0; j < lo.size(); j++)
				{
					if(lo.get(j).getId() == list2.get(i).getOrder().getId())
					{
						flag = false;
					}
				}
				if(flag)
				{
					lo.add(orderDAO.find(list2.get(i).getOrder().getId()));
				}
			}
		}
		
		return lo;
	}
	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	public IOrderItemDAO getOrderItemDAO() {
		return orderItemDAO;
	}
	public void setOrderItemDAO(IOrderItemDAO orderItemDAO) {
		this.orderItemDAO = orderItemDAO;
	}
	@Override
	public Order find(int id) {
		return orderDAO.find(id);
	}
	@Override
	public List<Order> find(User user) {
		return orderDAO.find(user);
	}
	@Override
	public OrderItem findItem(int id) {
		return orderItemDAO.find(id);
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public ICommodityDAO getCommodityDAO() {
		return commodityDAO;
	}
	public void setCommodityDAO(ICommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}
	@Override
	public List<OrderItem> findSellItem(User user, String state) {
		List<OrderItem> list = orderItemDAO.findAll();
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		for(int i = 0; i < list.size(); i++)
		{
			Commodity c = commodityDAO.find(list.get(i).getCommodity().getId());
			if(c.getUser().getId() == user.getId())
			{
				Order o = find(list.get(i).getOrder().getId());
				if(o.getState().equals(state))
				{
					list.get(i).setOrder(o);
					list.get(i).setCommodity(c);
					list2.add(list.get(i));
				}	
			}
			
		}
		return list2;
	}
	@Override
	public List<Order> findSell(User user, String state) {
		List<OrderItem> list = orderItemDAO.findAll();
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		for(int i = 0; i < list.size(); i++)
		{
			Commodity c = commodityDAO.find(list.get(i).getCommodity().getId());
			if(c.getUser().getId() == user.getId())
			{
				Order o = find(list.get(i).getOrder().getId());
				if(o.getState().equals(state))
				{
					list.get(i).setOrder(o);
					list.get(i).setCommodity(c);
					list2.add(list.get(i));
				}	
			}
			
		}
		List<Order> lo = new ArrayList<Order>();
		if(list2 != null)
		{
			for(int i = 0; i < list2.size(); i++)
			{
				boolean flag = true;
				for(int j = 0; j < lo.size(); j++)
				{
					if(lo.get(j).getId() == list2.get(i).getOrder().getId())
					{
						flag = false;
					}
				}
				if(flag)
				{
					lo.add(orderDAO.find(list2.get(i).getOrder().getId()));
				}
			}
		}
		
		return lo;
	}
	@Override
	public boolean updateOrder(Order order) {
		return orderDAO.update(order);
	}




}
