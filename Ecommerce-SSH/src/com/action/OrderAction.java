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

//添加订单、结账
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
		if(o.getState().equals("待发货"))
		{
			orderService.updateOrder(order, "已发货");
		}
		user = userService.findUser(user.getId());
		orders = orderService.findSell(user);
		orders = orderService.findConcreteOrder(orders);
		return "success";
	}
	public String findCommodities()//查找某用户某状态的订单项
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
			statetype = "待发货";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("2"))
		{
			statetype = "已发货";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("3"))
		{
			statetype = "已收货";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}else if(statetype.equals("4"))
		{
			statetype = "订单失败";
			//orderItems = orderService.findSellItem(user, statetype);
			orders = orderService.findSell(user, statetype);
		}
		orders = orderService.findConcreteOrder(orders);
		return "success";
		
	}
	public String beforeTuristPlaceOrder()//游客下单前
	{
		//if(user != null && user.getId() != 0)
		//{
		//	user = userService.findUser(user.getId());
		//}
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		
		if(commodity != null && commodity.getId() != 0)
		{
			System.out.println("commodity可行:"+commodity.getId());
			commodity = commodityService.findCommodity(commodity.getId());
			if(needNum > commodity.getNum())
			{
				msg="无法加入购物车";
				ActionContext.getContext().getSession().put("msg",msg);
				return "error";
			}
			count = commodity.getPrice()*needNum;
		}

		return "success";
		
	}
	public String turistplaceOrder(){//游客下订单
		//if(user != null && user.getId() != 0)
		//{//如果是用户
		//	user = userService.findUser(user.getId());
		//}
		//System.out.println("游客下订单:id:"+user.getId()+"name:"+user.getName());
	//	user = (User) ActionContext.getContext().getSession().get("user");
	//	ActionContext.getContext().getSession().put("user",user );

		commodity = commodityService.findCommodity(commodity.getId());
		System.out.println("商品:id:"+commodity.getId()+"name:"+commodity.getName());
		
		int n = commodity.getNum()-needNum;
		if(n >= 0)
		{
			//修改commodity库存
			commodity.setNum(n);
			commodityService.updateCommodity(commodity);
			//计算价格
			count = commodity.getPrice() * needNum;
			
			//添加订单
			int orderid = orderService.findAllOrder().size();
			System.out.println("订单id："+orderid);
			Date time = new Date();
			///////////////////
			System.out.println(time);
			////////
			
			order.setState("待发货");
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
			orderService.addOrderItem(o);//保存orderItem
		return "success";
		}
		else{
			return "error";
		}

	}
	
	
	public String beforePlaceOrder(){//从购物车下订单
		//user = userService.findUser(user.getId());
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		ActionContext.getContext().getSession().put("Score",String.valueOf(useScore));
		if(user != null)
		{
			if(useScore > user.getAccScore())
			{
				setMsg("积分不足");
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
	
	public String placeOrder(){//从购物车下订单
		//user = userService.findUser(user.getId());
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		useScore = Integer.parseInt((String)ActionContext.getContext().getSession().get("Score"));
		if(user != null)
		{
			//添加订单
			
			int score = user.getAccScore();
			int orderid = orderService.findAllOrder().size();
			cartItems = cartItemService.find(user);
			System.out.println("user不是空的：userid:"+user.getId()+" score:"+score+" orderid:"+orderid);
			int size = 0;
			count = 0;
			orders = new ArrayList<Order>();
			if(cartItems != null)
			{
				System.out.println("acrtItem不是空的");
				for(int i = 0; i < cartItems.size();i++)
				{//判断需要创建多少个order和相同数量的list<OrderItem>,并计算count
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
					if(!flag)//如果没有一样的
					{
						size++;
					}
				}
				System.out.println("ordersize:"+size);
				//计算总金额，积分等
				beforecount = count;
				count = count - useScore * 0.01;
				score = score - useScore;//用户剩余积分
				score = (int) (score + count);//
				user.setAccScore(score);
				System.out.println("userupdate:id:"+user.getId()+" name:"+user.getName()+" birthday:"+user.getBirthday()
						+" score:"+user.getAccScore());
				userService.updateUser(user);
				
				//持久化order和orderItem
				List<List<OrderItem>> lm = new ArrayList<List<OrderItem>>();
				for(int i = 0; i < size; i++)
				{
					//持久化size个order
					Order o = new Order();
					o.setAddress(order.getAddress());
					o.setId(orderid+1000+i);
					o.setName(order.getName());
					o.setPhonenumber(order.getPhonenumber());
					o.setState("待发货");
					Date time = new Date();
					o.setTime(time);
					o.setUser(user);
					o.setUseScore((int)(useScore/size));
					orderService.addOrder(o);
					orders.add(o);
					//创建size个List<OrderItem>
					List<OrderItem> mm = new ArrayList<OrderItem>();
					lm.add(mm);
				}
				for(int i = 0; i < cartItems.size(); i++)
				{
					//添加lm
					Commodity c = commodityService.findCommodity(cartItems.get(i).getCommodity().getId());
					OrderItem oitem = new OrderItem();
					oitem.setCommodity(c);
					oitem.setNeedNum(cartItems.get(i).getNeedNum());
					boolean flag = false;//标记是否已有order
					for(int j = 0; j < lm.size(); j++)
					{
						Order oo = orderService.find(orderid+1000+j);

							for(int k = 0; k < lm.get(j).size(); k++)
							{//如果第j列中有相同的发布者
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
							if(!flag && lm.get(j).size() == 0)//如果没有order
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
					orderService.addOrderItem(oitem);//添加订单项
					cartItemService.deleteCartItem(cartItems.get(i).getId());//删除购物车
				}
			}
			//显示内容
			
			//orders = orderService.find(user);//找到orders
			orders = orderService.findConcreteOrder(orders);//为orders附上orderItem的值
			
			
			
			return "success";
			}
		else{
			return "error";
		}
			
		/*if(user != null)
		{
			//添加订单
			int score = user.getAccScore();//积分
			int orderid = orderService.findAllOrder().size();
			System.out.println("订单订单订单id"+orderid);
			Date time = new Date();
			order.setState("待发货");
			order.setUser(user);
			order.setTime(time);
			order.setId(orderid+1000);
			order.setUseScore(useScore);
			orderService.addOrder(order);//保存order
			
			//添加订单项
			cartItems = cartItemService.find(user);
			orderItems = new ArrayList<OrderItem>();
			count = 0;
			if(cartItems != null && order != null)
			{
				for(int i = 0; i < cartItems.size(); i++)
				{
					OrderItem o = new OrderItem();
					Commodity c = commodityService.findCommodity(cartItems.get(i).getCommodity().getId());//获得commodity
					o.setCommodity(c);
					o.setNeedNum(cartItems.get(i).getNeedNum());
					o.setOrder(order);
					orderItems.add(o);//方便显示
					orderService.addOrderItem(o);//保存orderItem
					cartItemService.deleteCartItem(cartItems.get(i).getId());
					count += c.getPrice() * cartItems.get(i).getNeedNum();
				}
				count = count - useScore * 0.01;
				score = score - useScore;
				//增加积分//积分=总金额
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
	public String showUserOrder()//显示某用户的订单
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		user = userService.findUser(user.getId());
		orders = orderService.find(user);//找到orders
		orders = orderService.findConcreteOrder(orders);//为orders附上orderItem的值
		return "success";
		
	}
	public String showNeedFinishOrder(){//显示需完成订单
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		user = userService.findUser(user.getId());
		orders = orderService.findSell(user);
		orders = orderService.findConcreteOrder(orders);
		//orderItems = orderService.findSellItem(user);
		return "success";
	}
	public String updateOrderStateSell(){//修改订单状态
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		System.out.println("*****************************"+order.getState());
		Order o = orderService.find(order.getId());
		if(o.getState().equals("已收货") || o.getState().equals("订单失败"))
		{
			msg = "确定后无法修改";
		}
		else
		{
			if(order.getState().equals("yes"))
			{
				//order = orderService.find(order.getId());
				//order.setState("已收货");
				orderService.updateOrder(order, "已收货");
			}
			else if(order.getState().equals("no"))
			{
				orderService.updateOrder(order, "订单失败");
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
		if(o.getState().equals("待发货"))
		{
			orderService.updateOrder(o, "已发货");
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
