package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.po.CartItem;
import com.po.Commodity;
import com.po.Order;
import com.po.OrderItem;
import com.po.User;
import com.service.ICartItemService;
import com.service.ICommodityService;
import com.service.IOrderService;
import com.service.IUserService;

//��Ӷ���������
//@Controller
public class OrderAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Commodity commodity;
	private CartItem cartItem;
	private Order order;
	private IOrderService orderService;
	private ICartItemService cartItemService;
	private ICommodityService commodityService;
	private IUserService userService;
	private List<CartItem> cartItems;
	private List<OrderItem> orderItems;
	private List<Order> orders;
	private double count;
	private int needNum;
	private int useScore = 0;
	private String msg = "";
	private String stringid;
	private double beforecount;
	private String statetype;

	public String updateOrderSell()
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		Order o = orderService.find(order.getId());
		if(o.getState().equals("������"))
		{
			orderService.updateOrder(order, "�ѷ���");
		}
		user = userService.findUser(user.getId());
		orders = orderService.findSell(user);
		orders = orderService.findConcreteOrder(orders);
		return "success";
	}
	public String findCommodities()//����ĳ�û�ĳ״̬�Ķ�����
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		user = userService.findUser(user.getId());
		if(statetype.equals(""))
		{
			//orderItems = orderService.findSellItem(user);
			orders = orderService.findSell(user);
		}else if(statetype.equals("1"))
		{
			statetype = "������";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("2"))
		{
			statetype = "�ѷ���";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("3"))
		{
			statetype = "���ջ�";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("4"))
		{
			statetype = "����ʧ��";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}
		orders = orderService.findConcreteOrder(orders);
		return "success";
		
	}
	public String beforeTuristPlaceOrder()//�ο��µ�ǰ
	{
		//if(user != null && user.getId() != 0)
		//{
		//	user = userService.findUser(user.getId());
		//}
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		
		if(commodity != null && commodity.getId() != 0)
		{
			System.out.println("commodity����:"+commodity.getId());
			commodity = commodityService.findCommodity(commodity.getId());
			if(needNum > commodity.getNum())
			{
				msg="�޷����빺�ﳵ";
				ActionContext.getContext().getSession().put("msg",msg);
				return "error";
			}
			count = commodity.getPrice()*needNum;
		}

		return "success";
		
	}
	public String turistplaceOrder(){//�ο��¶���
		//if(user != null && user.getId() != 0)
		//{//������û�
		//	user = userService.findUser(user.getId());
		//}
		//System.out.println("�ο��¶���:id:"+user.getId()+"name:"+user.getName());
	//	user = (User) ActionContext.getContext().getSession().get("user");
	//	ActionContext.getContext().getSession().put("user",user );

		commodity = commodityService.findCommodity(commodity.getId());
		System.out.println("��Ʒ:id:"+commodity.getId()+"name:"+commodity.getName());
		
		int n = commodity.getNum()-needNum;
		if(n >= 0)
		{
			//�޸�commodity���
			commodity.setNum(n);
			commodityService.updateCommodity(commodity);
			//����۸�
			count = commodity.getPrice() * needNum;
			
			//��Ӷ���
			int orderid = orderService.findAllOrder().size();
			System.out.println("����id��"+orderid);
			Date time = new Date();
			///////////////////
			System.out.println(time);
			////////
			
			order.setState("������");
			if(user != null)
			{
				if(user.getId() != 0)
					order.setUser(user);
			}
			order.setTime(time);
			order.setId(orderid+1000);
			orderService.addOrder(order);
			
			OrderItem o = new OrderItem();
			o.setCommodity(commodity);
			o.setNeedNum(needNum);
			o.setOrder(order);
			orderService.addOrderItem(o);//����orderItem
		return "success";
		}
		else{
			return "error";
		}

	}
	
	
	public String beforePlaceOrder(){//�ӹ��ﳵ�¶���
		//user = userService.findUser(user.getId());
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		ActionContext.getContext().getSession().put("Score",String.valueOf(useScore));
		if(user != null)
		{
			if(useScore > user.getAccScore())
			{
				setMsg("���ֲ���");
				return "error2";
			}
			else
			{
				cartItems = cartItemService.findDetial(user);
				beforecount = cartItemService.count(user, 0);
				count = beforecount - useScore * 0.01;
				return "success";
			}
		}
		else{
			return "error";
		}
	}
	
	public String placeOrder(){//�ӹ��ﳵ�¶���
		//user = userService.findUser(user.getId());
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		useScore = Integer.parseInt((String)ActionContext.getContext().getSession().get("Score"));
		if(user != null)
		{
			//��Ӷ���
			
			int score = user.getAccScore();
			int orderid = orderService.findAllOrder().size();
			cartItems = cartItemService.find(user);
			System.out.println("user���ǿյģ�userid:"+user.getId()+" score:"+score+" orderid:"+orderid);
			int size = 0;
			count = 0;
			orders = new ArrayList<Order>();
			if(cartItems != null)
			{
				System.out.println("acrtItem���ǿյ�");
				for(int i = 0; i < cartItems.size();i++)
				{//�ж���Ҫ�������ٸ�order����ͬ������list<OrderItem>,������count
					boolean flag = false;
					Commodity c = commodityService.findCommodity(cartItems.get(i).getCommodity().getId());
					count += c.getPrice() * cartItems.get(i).getNeedNum();
					for(int j = i+1; j < cartItems.size() && j != i; j++)
					{
						Commodity c2 = commodityService.findCommodity(cartItems.get(j).getCommodity().getId());
						if(c.getUser().getId() == c2.getUser().getId())
						{
							flag = true;
						}
					}
					if(!flag)//���û��һ����
					{
						size++;
					}
				}
				System.out.println("ordersize:"+size);
				//�����ܽ����ֵ�
				beforecount = count;
				count = count - useScore * 0.01;
				score = score - useScore;//�û�ʣ�����
				score = (int) (score + count);//
				user.setAccScore(score);
				System.out.println("userupdate:id:"+user.getId()+" name:"+user.getName()+" birthday:"+user.getBirthday()
						+" score:"+user.getAccScore());
				userService.updateUser(user);
				
				//�־û�order��orderItem
				List<List<OrderItem>> lm = new ArrayList<List<OrderItem>>();
				for(int i = 0; i < size; i++)
				{
					//�־û�size��order
					Order o = new Order();
					o.setAddress(order.getAddress());
					o.setId(orderid+1000+i);
					o.setName(order.getName());
					o.setPhonenumber(order.getPhonenumber());
					o.setState("������");
					Date time = new Date();
					o.setTime(time);
					o.setUser(user);
					o.setUseScore((int)(useScore/size));
					orderService.addOrder(o);
					orders.add(o);
					//����size��List<OrderItem>
					List<OrderItem> mm = new ArrayList<OrderItem>();
					lm.add(mm);
				}
				for(int i = 0; i < cartItems.size(); i++)
				{
					//���lm
					Commodity c = commodityService.findCommodity(cartItems.get(i).getCommodity().getId());
					OrderItem oitem = new OrderItem();
					oitem.setCommodity(c);
					oitem.setNeedNum(cartItems.get(i).getNeedNum());
					boolean flag = false;//����Ƿ�����order
					for(int j = 0; j < lm.size(); j++)
					{
						Order oo = orderService.find(orderid+1000+j);

							for(int k = 0; k < lm.get(j).size(); k++)
							{//�����j��������ͬ�ķ�����
								System.out.println("kkkkk"+k);
								System.out.println("c.getUser().getId():"+c.getUser().getId());
								System.out.println("lm.get(j).get(k).getCommodity().getUser().getId():"+lm.get(j).get(k).getCommodity().getUser().getId());
								if(c.getUser().getId() == lm.get(j).get(k).getCommodity().getUser().getId())
								{
									oitem.setOrder(oo);
									lm.get(j).add(oitem);
									flag = true;
									break;
								}
							}
							if(!flag && lm.get(j).size() == 0)//���û��order
							{
								System.out.println("lm.get(j).size() == 0:"+j+"commodityid:"+c.getId());
								oitem.setOrder(oo);
								lm.get(j).add(oitem);
								flag = true;
							}
							if(flag)
							{
								break;
							}
					}
					orderService.addOrderItem(oitem);//��Ӷ�����
					cartItemService.deleteCartItem(cartItems.get(i).getId());//ɾ�����ﳵ
				}
			}
			//��ʾ����
			
			//orders = orderService.find(user);//�ҵ�orders
			orders = orderService.findConcreteOrder(orders);//Ϊorders����orderItem��ֵ
			
			
			
			return "success";
			}
		else{
			return "error";
		}
			
		/*if(user != null)
		{
			//��Ӷ���
			int score = user.getAccScore();//����
			int orderid = orderService.findAllOrder().size();
			System.out.println("������������id"+orderid);
			Date time = new Date();
			order.setState("������");
			order.setUser(user);
			order.setTime(time);
			order.setId(orderid+1000);
			order.setUseScore(useScore);
			orderService.addOrder(order);//����order
			
			//��Ӷ�����
			cartItems = cartItemService.find(user);
			orderItems = new ArrayList<OrderItem>();
			count = 0;
			if(cartItems != null && order != null)
			{
				for(int i = 0; i < cartItems.size(); i++)
				{
					OrderItem o = new OrderItem();
					Commodity c = commodityService.findCommodity(cartItems.get(i).getCommodity().getId());//���commodity
					o.setCommodity(c);
					o.setNeedNum(cartItems.get(i).getNeedNum());
					o.setOrder(order);
					orderItems.add(o);//������ʾ
					orderService.addOrderItem(o);//����orderItem
					cartItemService.deleteCartItem(cartItems.get(i).getId());
					count += c.getPrice() * cartItems.get(i).getNeedNum();
				}
				count = count - useScore * 0.01;
				score = score - useScore;
				//���ӻ���//����=�ܽ��
				System.out.println("user:id:"+user.getId()+" name:"+user.getName()+" birthday:"+user.getBirthday()
						+" score:"+user.getAccScore());
				
				score = (int) (score + count);
				user.setAccScore(score);
				System.out.println("userupdate:id:"+user.getId()+" name:"+user.getName()+" birthday:"+user.getBirthday()
						+" score:"+user.getAccScore());
				userService.updateUser(user);
				
				return "success";
			}
			else
			{
				return "error";
			}
		}
		else{
			return "error";
		}*/
	}
	public String showUserOrder()//��ʾĳ�û��Ķ���
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		user = userService.findUser(user.getId());
		orders = orderService.find(user);//�ҵ�orders
		orders = orderService.findConcreteOrder(orders);//Ϊorders����orderItem��ֵ
		return "success";
		
	}
	public String showNeedFinishOrder(){//��ʾ����ɶ���
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		user = userService.findUser(user.getId());
		orders = orderService.findSell(user);
		orders = orderService.findConcreteOrder(orders);
		//orderItems = orderService.findSellItem(user);
		return "success";
	}
	public String updateOrderStateSell(){//�޸Ķ���״̬
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		System.out.println("*****************************"+order.getState());
		Order o = orderService.find(order.getId());
		if(o.getState().equals("���ջ�") || o.getState().equals("����ʧ��"))
		{
			msg = "ȷ�����޷��޸�";
		}
		else
		{
			if(order.getState().equals("yes"))
			{
				//order = orderService.find(order.getId());
				//order.setState("���ջ�");
				orderService.updateOrder(order, "���ջ�");
			}
			else if(order.getState().equals("no"))
			{
				orderService.updateOrder(order, "����ʧ��");
			}
		}
		
		return "success";
	}
	public String updateStateSeller()
	{
		//user = (User) ActionContext.getContext().getSession().get("user");
		//ActionContext.getContext().getSession().put("user",user );
		//if(order != null)
		//	System.out.println(" updateStateSeller id:"+order.getId());
		//else
		//	System.out.println(" updateStateSeller is null");

		Order o = orderService.find(Integer.parseInt(stringid));
		if(o.getState().equals("������"))
		{
			orderService.updateOrder(o, "�ѷ���");
		}

		return "success";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public CartItem getCartItem() {
		return cartItem;
	}
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}
	public ICartItemService getCartItemService() {
		return cartItemService;
	}
	public void setCartItemService(ICartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	public ICommodityService getCommodityService() {
		return commodityService;
	}
	public void setCommodityService(ICommodityService commodityService) {
		this.commodityService = commodityService;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}

	public int getNeedNum() {
		return needNum;
	}

	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}
	public int getUseScore() {
		return useScore;
	}
	public void setUseScore(int useScore) {
		this.useScore = useScore;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public double getBeforecount() {
		return beforecount;
	}
	public void setBeforecount(double beforecount) {
		this.beforecount = beforecount;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getStringid() {
		return stringid;
	}
	public void setStringid(String stringid) {
		this.stringid = stringid;
	}
	public String getStatetype() {
		return statetype;
	}
	public void setStatetype(String statetype) {
		this.statetype = statetype;
	}
}
