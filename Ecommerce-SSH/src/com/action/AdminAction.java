package com.action;

//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.po.Admin;
import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.Order;
import com.po.OrderItem;
import com.po.User;
import com.service.*;
//@Controller@Scope("prototype")
public class AdminAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAdminService adminService;
	private IUserService userService;
	private ICommodityService commodityService;
	private IOrderService orderService;
	private List<User> userlist;
	private List<Commodity> commoditylist;
	private List<Order> orderlist;
	private List<OrderItem> orderItemlist;
	private List<LeaveMessage> leaveMessagelist;
	private Admin admin;
	private User user,user2;
	private Order order;
	private Commodity commodity;
	private LeaveMessage leaveMessage;
	public String stringid;
	private String msg = "";
	private String statetype="";
	private String propertytype="";
	private double count;
	
	public AdminAction()
	{
		System.out.println("creatAdminAction");
	}
	public String updateOrder()//���¶���
	{
		stringid = (String) ActionContext.getContext().getSession().get("stringid");
		System.out.println("���¶���"+order.getId());
		Order o = orderService.find(Integer.parseInt(stringid));
		o.setAddress(order.getAddress());
		o.setName(order.getName());
		o.setPhonenumber(order.getPhonenumber());
		orderService.updateOrder(o);
		order = o;
		return "success";
		
	}
	public String findleaveMessages()//��������
	{
		if(user != null)
			System.out.println("leavem��������"+user.getId());
		else
			System.out.println("leavem���������ǿյ�");
		if(commodity != null)
			System.out.println("leavem������c��"+commodity.getId());
		else
			System.out.println("leavem������c���ǿյ�");
		leaveMessagelist = userService.findleaveMessageByUaC(user, commodity);
		leaveMessagelist = userService.findLeaveMessage(leaveMessagelist);
		user.setId(0);
		commodity.setId(0);
		return "success";
		
	}
	public String deleteMessage()
	{
		if(leaveMessage != null)
		{
			leaveMessage = userService.findLeaveMessage(leaveMessage);
			userService.deleteLeaveMessage(leaveMessage);
		}
		
		return "success";
		
	}
	public String showAllLeaveMessage()
	{
		leaveMessagelist = userService.findAllLeaveMessage();
		return "success";
	}
	public String addAdmin()
	{
		System.out.println("-----adminService.save()-----");
		//System.out.println("id:"+user.getId()+"name:"+user.getName()+" password:"+user.getPassword()+" address:"+user.getAddress());
		if(adminService.registerAdmin(admin))
		{
			return "success";
		}
		else
		{
			if(adminService.getMsg().equals("���û����ѱ�ע��"))
			{
				msg = adminService.getMsg();
				return "input";
			}else{
				return "error";
			}
		}

	}
	public String updateAdmin()
	{
		Admin u = (Admin) ActionContext.getContext().getSession().get("admin");
		admin.setId(u.getId());
		ActionContext.getContext().getSession().put("admin",admin );
		
		try{
			adminService.updateAdmin(admin);
			return "success";
		}catch(Exception e)
		{
			return "error";
		}
	}
	public String login(){
		admin = (Admin)ActionContext.getContext().getSession().get("admin");
		if(admin != null)
		{
			System.out.println("adminname: "+admin.getName()+" adminpassword:"+admin.getPassword());
			admin = adminService.loginAdmin(admin);
			ActionContext.getContext().getSession().put("admin",admin );
			if(admin != null)
				return "success";
		}
		else{
			System.out.println("admin is null");
		}
		msg = "�û������������";
		return "error";
	}
	public String logout(){
		admin = null;
		user = null;
		ActionContext.getContext().getSession().put("admin",admin );
		return "success";
	}
	public String showAllUser()//��ʾ�����û�
	{
		//admin = (Admin)ActionContext.getContext().getSession().get("admin");
		//ActionContext.getContext().getSession().put("admin",admin );
		System.out.println("showUser");
		userlist = userService.findAllUser();
		if(userlist != null)
		{
			for(int i = 0; i < userlist.size();i++)
			{
				System.out.println("userid:"+userlist.get(i).getId()+"name:"+userlist.get(i).getName());
			}
			return "success";
		}
		else{
			msg = "û���û�";
			return "success";
		}
	}
	public String findUsers()//��������ѯ�û�
	{
		//admin = (Admin)ActionContext.getContext().getSession().get("admin");
		//ActionContext.getContext().getSession().put("admin",admin );
		if(user != null)
			System.out.println("adminuser:id:"+user.getId()+"name"+user.getName()+"password:"+user.getPassword()+"address:"+user.getAddress());
		userlist = userService.findUser(user);
		return "success";
	}
	public String beforeupdateUser()//�޸���Ϣ֮ǰ��д��
	{
		user = userService.findUser(user.getId());
		user2 = user;
		ActionContext.getContext().getSession().put("user",user );
		if(user != null)
			System.out.println("useruuuuuuuuuuuu:"+user.getId()+" name:"+user.getName());
		return "success";
	}
	public String updateUser()//�޸��û���Ϣ
	{
		if(user != null)
		{
			User u = (User)ActionContext.getContext().getSession().get("user");
			user.setId(u.getId());
			userService.updateUser(user);
		}
		return "success";
	}
	public String deleteUser()//ɾ���û���Ϣ
	{
		if(user != null)
		{
			System.out.println("user != null id:"+user.getId());
			userService.deleteUser(user);
		}
		else{
			System.out.println("user == null");
		}
		return "success";
	}
	public String showAllCommodity()//��ʾ������Ʒ
	{
		commoditylist = commodityService.findAllCommodity();
		if(commoditylist != null)
		{
			for(int i = 0; i < commoditylist.size();i++)
			{
				System.out.println("commoditylistid:"+commoditylist.get(i).getId()+"name:"+commoditylist.get(i).getName());
			}
			return "success";
		}
		else{
			msg = "û����Ʒ";
			return "success";
		}
	}
	public String findCommodities()//������������Ʒ
	{
		admin = (Admin)ActionContext.getContext().getSession().get("admin");
		ActionContext.getContext().getSession().put("admin",admin );
		if(commodity != null)
		{
			System.out.println("admincommodity:id:"+commodity.getId()+"name"+commodity.getName()+"property:"+commodity.getProperty());
			if(statetype.equals("0"))
			{
				commodity.setState("");
			}
			else if(statetype.equals("1"))
			{
				commodity.setState("�����");
			}
			else if(statetype.equals("2")){
				commodity.setState("�����");
			}else if(statetype.equals("3")){
				commodity.setState("��ͨ��");
			}else if(statetype.equals("4")){
				commodity.setState("���¼�");
			}
			
			if(propertytype.equals("0"))
			{
				commodity.setProperty("");
			}else if(propertytype.equals("1"))
			{
				commodity.setProperty("����Ʒ");
			}else if(propertytype.equals("2"))
			{
				commodity.setProperty("��ʳ");
			}else if(propertytype.equals("3"))
			{
				commodity.setProperty("���Ӳ�Ʒ");
			}
			commoditylist = commodityService.findCommodity(commodity);
		}
		return "success";
	}
	public String showAllOrder()//��ʾ���ж���
	{
		//orderlist = orderService.findConcreteOrder();
		orderlist = orderService.findAllOrder();
		if(orderlist != null)
		{
			for(int i = 0; i < orderlist.size();i++)
			{
				if(orderlist.get(i).getUser() == null)
				{
					System.out.println("�ο��ǿյ�");
				}
				else{
					int id = orderlist.get(i).getUser().getId();
					orderlist.get(i).setUser(userService.findUser(id));
				}
				System.out.println("orderlistid:"+orderlist.get(i).getId()+"name:"+orderlist.get(i).getName());
			}
			return "success";
		}
		else{
			msg = "û�ж���";
			return "success";
		}
	}
	public String findOrders()//��������ѯ����
	{
		System.out.println("11111111111111111111111111");
		if(order != null)
		{
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("orderid:"+order.getId());
			System.out.println("ordername:"+order.getName());
			if(order.getUser()!=null)
			{
				System.out.println("order.user.name:"+order.getUser().getId());
				System.out.println("order.user.name:"+order.getUser().getName());
			}
			if(statetype.equals("0"))
			{
				order.setState("");
			}
			else if(statetype.equals("1"))
			{
				order.setState("����ʧ��");
			}
			else if(statetype.equals("2")){
				order.setState("������");
			}else if(statetype.equals("3")){
				order.setState("�ѷ���");
			}else if(statetype.equals("4")){
				order.setState("���ջ�");
			}
			orderlist = orderService.findAllOrder(order);
		}
		return "success";
	}
	public String findOrderItems()//ͨ������id��ѯ
	{
		if(order.getId() != 0)
		{
			count = 0;
			orderItemlist = orderService.findAllOrderItem(order);
			if(orderItemlist != null)
			{
				for(int i = 0; i < orderItemlist.size(); i++)
				{
					System.out.println("orderItemlist id:"+orderItemlist.get(i).getId());
					count += orderItemlist.get(i).getCommodity().getPrice() * orderItemlist.get(i).getNeedNum();
				}
			}
			else
			{
				System.out.println("orderItemlist is null");
			}
			order = orderService.find(order.getId());
			ActionContext.getContext().getSession().put("stringid",String.valueOf(order.getId()) );
		}
		return "success";
	}
	public String findCommodity()//ͨ��commodity.id��ѯ
	{
		if(commodity.getId() != 0)
		{
			commodity = commodityService.findCommodity(commodity.getId());
		}
		return "success";
		
	}
	public String passCommodity()//�����Ʒ
	{
		System.out.println("�����Ʒ"+commodity.getId()+"name:"+commodity.getName()+"��ˣ�"+commodity.getState());
		Commodity c= commodityService.findCommodity(commodity.getId());
		System.out.println("�����Ʒc"+c.getId()+"name:"+c.getName()+"��ˣ�"+c.getState());
		if(commodity != null)
		{
			System.out.println("commodity���ǿյ�");
			if(commodity.getState().equals("yes"))
			{
				System.out.println("commodityyishenhe");
				if(c.getState().equals("�����"))
				{
					msg = "����Ʒ����ˣ�";	
				}
				else
				{
					c.setState("�����");
					commodityService.updateCommodity(c);
				}
			}
			else if(commodity.getState().equals("no"))
			{
				System.out.println("butongguuo");
				if(c.getState().equals("��ͨ��"))
				{
					msg = "����Ʒ�Ѳ�ͨ����";	
				}
				else
				{
					c.setState("��ͨ��");
					commodityService.updateCommodity(c);
				}
			}
		
		}
		commodity = c;
		System.out.println("commodity:��˺�"+commodity.getState());
		return "success";
	}
	public String adminHome()
	{
		
		if(msg.equals("update"))
			admin = adminService.find(admin.getId());
		else
			admin = (Admin)ActionContext.getContext().getSession().get("admin");
		ActionContext.getContext().getSession().put("admin",admin );
		return "success";
	}
	public String beforeaddAdmin()//��ӹ���Ա����
	{
		return "success";
	}
	public String beforeupdateAdmin()//�޸���Ϣ����
	{
		admin = (Admin)ActionContext.getContext().getSession().get("admin");
		ActionContext.getContext().getSession().put("admin",admin );
		return "success";
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public IAdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Commodity> getCommoditylist() {
		return commoditylist;
	}

	public void setCommoditylist(List<Commodity> commoditylist) {
		this.commoditylist = commoditylist;
	}

	public ICommodityService getCommodityService() {
		return commodityService;
	}

	public void setCommodityService(ICommodityService commodityService) {
		this.commodityService = commodityService;
	}
	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public List<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}


	public List<OrderItem> getOrderItemlist() {
		return orderItemlist;
	}

	public void setOrderItemlist(List<OrderItem> orderItemlist) {
		this.orderItemlist = orderItemlist;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getStatetype() {
		return statetype;
	}

	public void setStatetype(String statetype) {
		this.statetype = statetype;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public String getPropertytype() {
		return propertytype;
	}

	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}
	public List<LeaveMessage> getLeaveMessagelist() {
		return leaveMessagelist;
	}
	public void setLeaveMessagelist(List<LeaveMessage> leaveMessagelist) {
		this.leaveMessagelist = leaveMessagelist;
	}
	public String getStringid() {
		return stringid;
	}
	public void setStringid(String stringid) {
		this.stringid = stringid;
	}
	public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
}
