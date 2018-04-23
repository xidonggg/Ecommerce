package com.action;



//import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.po.Admin;
import com.po.User;
import com.service.IUserService;

//@Controller
public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private User user;
	private String repwd;
	private String msg = "";//���ֶ�����У�����ʱ�ķ�����Ϣ
	private String usertype="";
	
	public UserAction()
	{
		System.out.println("create UserAction");
	}

	//-------------------------------------������---------------------------------------

	public String register(){//ע��
		System.out.println("id:"+user.getId()+"name:"+user.getName()+" password:"+user.getPassword()+" address:"+user.getAddress());
		//user.setId(5);
		user.setAccScore(0);
		if(userService.registerUser(user))
		{
			ActionContext.getContext().getSession().put("user",user );
			return "success";
		}
		else
		{
			if(userService.getMsg().equals("���û����ѱ�ע��"))
			{
				msg = userService.getMsg();
				return "input";
			}
			else{
				return "error";
			}
		}

	}
	public String beforelogin()
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		return "success";
	}
	public String login()//��½
	{
		System.out.println("-----login----");
		System.out.println(usertype);
		//user = (User) ActionContext.getContext().getSession().get("user");
		//System.out.println("login:id��"+user.getId());
		if(usertype.equals("1"))//&& (user == null || user.getId() == 0))
		{
			System.out.println("-----login.find.start-----");
			user = userService.loginfind(user);
			System.out.println("-----login.find.end-----");
			
			if(!(user==null))
			{
				System.out.println("naem:"+user.getName());
				ActionContext.getContext().getSession().put("user",user );
				return "success";
			}
			else{
				msg = "�û������������";
				return "error";	
			}
		}
		else if(usertype.equals("2"))// &&(user == null || user.getId() == 0))
		{
			Admin admin = new Admin();
			admin.setName(user.getName());
			admin.setPassword(user.getPassword());
			ActionContext.getContext().getSession().put("admin",admin );
			System.out.println("useradmin:id:"+admin.getId()+"name:"+admin.getName());
			return "jump";
		}
		else{
			System.out.println("-----user���ǿյ�----");
			System.out.println("userid:"+user.getId()+" name:"+user.getName()+"accScore:"+user.getAccScore());
			msg = "�û������������";
			return "error";
		}

	}
	public String logout()//ע����½
	{
		user = null;
		ActionContext.getContext().getSession().put("user",user );
		return "success";
	}
	public String userHome(){//��ʾ�û�������Ϣ
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		
		//if(user != null && user.getId() != 0){
		//	user = userService.findUser(user.getId());
		//}
		if(user != null)
			return "success";
		else
			return "error";
	}
	public String update()//�޸��û���Ϣ
	{
		User u = (User) ActionContext.getContext().getSession().get("user");
		//ActionContext.getContext().getSession().put("user",user );
		//User u = userService.findUser(user.getId());
		user.setId(u.getId());
		ActionContext.getContext().getSession().put("user",user );
		user.setAccScore(u.getAccScore());
		user.setBirthday(u.getBirthday());
		
		try{
			userService.updateUser(user);
			return "success";
		}catch(Exception e)
		{
			return "error";
		}
	}
	public String beforeupdate()
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		//user = userService.findUser(user.getId());
		return "success";
	}
	public String beforeregister()
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		//user = userService.findUser(user.getId());
		return "success";
	}
	/*public Map<String,String> getStrList() {
		System.out.println("getStrList!");
		Map<String,String> list = new HashMap<String,String>();
		list.put("id1", "�û�");
		list.put("id2", "����Ա");
		this.strList = list;
		return this.strList;
	}
	public void setStrList(Map<String,String> strList) {
		this.strList = strList;
	}
	*/
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IUserService getUserService(){
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		System.out.println("-----setUserService-----");
		this.userService = userService;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}