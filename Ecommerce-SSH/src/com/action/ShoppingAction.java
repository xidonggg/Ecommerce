package com.action;

import java.util.List;

//import org.springframework.stereotype.Controller;







import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.po.CartItem;
import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;
import com.service.ICartItemService;
import com.service.ICommodityService;
import com.service.IUserService;

//@Controller
@SuppressWarnings("serial")
public class ShoppingAction extends ActionSupport{
	private User user;
	private Commodity commodity;
	private CartItem cartItem;
	private ICartItemService cartItemService;
	private ICommodityService commodityService;
	private IUserService userService;
	private List<CartItem> cartItems;
	private List<LeaveMessage> leaveMessages;
	private String msg;
	private int needNum;
	private double count;
	private int cartItemid;

	public String showCart(){//显示购物车
		//user = userService.findUser(user.getId());
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		msg = "";
		if(user != null)
		{
			System.out.println("showCart");
			System.out.println("userid"+user.getId()+" name:"+user.getName());
			cartItems = cartItemService.findDetial(user);	
			count = cartItemService.count(user,0);
			if(cartItems == null)
			{
				msg = "null";
			}
			ActionContext.getContext().getSession().put("cart",msg);
			return "success";
		}
		else
			return "error";
	}
	public String addCart()//添加购物车
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		commodity = commodityService.findCommodity(commodity.getId());
		//if(user != null && user.getId() != 0)
		//{
		//	user = userService.findUser(user.getId());
		//}
		//else{
		//	return "error";
		//
		if(user == null || user.getId() == 0)
		{
			msg="";
			return "error";
		}
		System.out.println("aaaaaaaaaaddCart");

		System.out.println("needNum:" + needNum);
		System.out.println("userid:"+user.getId()+" name:"+user.getName());
			
		int n = commodity.getNum()-needNum;
		if(n >= 0 && needNum != 0)
		{
			commodity.setNum(n);
			commodityService.updateCommodity(commodity);
	
			CartItem c = new CartItem();
			c.setCommodity(commodity);
			c.setNeedNum(needNum);
			c.setUser(user);
			cartItemService.addCartItem(c);
				
			ActionContext.getContext().getSession().put("commodity",commodity );
			ActionContext.getContext().getSession().put("user",user );
			msg = "添加成功！";
				//out.print("javascript:alert('我是普通按钮！')");
		}
		else
		{
			msg = "请重新填写商品数量!";
		}	
		leaveMessages = userService.findleaveMessage(commodity);
		System.out.println("commodityid:"+commodity.getId());
		if(user != null && user.getId() != 0){
			System.out.println("userid:"+user.getId());
			user = userService.findUser(user.getId());
		}
		//留言
		if(leaveMessages != null)
		{
			System.out.println(leaveMessages.size());
			for(int i = 0; i< leaveMessages.size();i++)
			{
				System.out.println("leaveMessage:"+leaveMessages.get(i).getContact());
				if(leaveMessages.get(i).getUser() == null)
				{
					System.out.print("leaveMessage的外键的User是空的");
					User u = new User();
					u.setName("游客");
					leaveMessages.get(i).setUser(u);
				}
				else if(leaveMessages.get(i).getUser().getId() == 0)
				{
					System.out.print("leaveMessage的外键的User不是空的");
					leaveMessages.get(i).getUser().setName("游客");
				}
			}
		}
		else{
			System.out.println("leaveMessage is null");
		}
		return "success";

	}
	public String deleteItem()//删除某商品
	{
		//if(user != null && user.getId() != 0)
		//{
		//	user = userService.findUser(user.getId());
		//}
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		
		CartItem c = cartItemService.find(cartItemid);//得到cart Item
		commodity = commodityService.findCommodity(c.getCommodity().getId());//得到commodity
		commodity.setNum(commodity.getNum()+c.getNeedNum());	

		commodity.getCartItemlist().remove(c);//删掉对应cartItem****
		System.out.println();
	//-----------------------------------------------------？？？？？？？？？？？？？？？？？？
		System.out.println("cartItemid:"+cartItemid);
		
		commodityService.updateCommodity(commodity);//保存修改
		cartItemService.deleteCartItem(cartItemid);//删除
		//ActionContext.getContext().getSession().put("user",user );
		showCart();
		return "success";
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ICartItemService getCartItemService() {
		return cartItemService;
	}
	public void setCartItemService(ICartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public ICommodityService getCommodityService() {
		return commodityService;
	}
	public void setCommodityService(ICommodityService commodityService) {
		this.commodityService = commodityService;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
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
	public int getNeedNum() {
		return needNum;
	}
	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCartItemid() {
		return cartItemid;
	}
	public void setCartItemid(int cartItemid) {
		this.cartItemid = cartItemid;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public List<LeaveMessage> getLeaveMessages() {
		return leaveMessages;
	}
	public void setLeaveMessages(List<LeaveMessage> leaveMessages) {
		this.leaveMessages = leaveMessages;
	}
}
