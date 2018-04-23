package com.action;

import java.util.List;

//import org.springframework.stereotype.Controller;








import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;
import com.service.ICommodityService;
import com.service.IUserService;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//��ʾ��Ʒ����ӹ��ﳵ
//@Controller
public class CommodityAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Commodity commodity;
	private User user;
	private String msg = "";//���ֶ�����У�����ʱ�ķ�����Ϣ
	private ICommodityService commodityService;
	private IUserService userService;
	private List<Commodity> commodities;//������Ʒ
	private LeaveMessage leaveMessage;
	private List<LeaveMessage> leaveMessages;
	private String property = "";
	
	private File image;//�ϴ����ļ�
	private String imageFileName;//�ļ�����
	private String imageContentType;//�ļ�����
	private String dataUrl;
	
	public String updateCommodityState()//�¼�
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		commodity = commodityService.findCommodity(commodity.getId());
		if(commodity != null)
		{
			if(commodity.getState().equals("�����"))
			{
				commodity.setState("���¼�");
				commodityService.updateCommodity(commodity);
			}
		}
		return "success";
	}
	public String showMyCommodity()//��ʾ���ҡ���������Ʒ
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		commodities = commodityService.findCommodity(user);
		return "success";
	}
	public String beforeaddCommodity()
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		if(user != null && user.getId() != 0)
			user = userService.findUser(user.getId());
		ActionContext.getContext().getSession().put("user",user );
		return "success";
	}
	public String addCommodity() throws IOException//�����Ʒ
	{
		user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("user",user );
		System.out.println("-----commodityService.add()-----");
		//���user���
		if(user != null && user.getId() != 0)
			user = userService.findUser(user.getId());
		
		System.out.println("id:"+commodity.getId()+" name:"+commodity.getName()+" property:"+commodity.getProperty()+" describe:"+commodity.getComdescribe());
		if(user != null)
			System.out.println("***userid:"+user.getId()+" name:"+user.getName()+" address:"+user.getAddress());
		else
			System.out.println("user is null");
		commodity.setUser(user);
		//�������
		if(property.equals("1"))
		{
			commodity.setProperty("����Ʒ");
		}
		else if(property.equals("2")){
			commodity.setProperty("��ʳ");
		}else if(property.equals("3")){
			commodity.setProperty("���Ӳ�Ʒ");
		}
		//���ͼƬ·��
			String realpath ="E:/workspace/eclipse_jee_old_workspace/Ecommerce-SSH/WebContent/images";
			//String realpath = ServletActionContext.getServletContext().getRealPath("/images");
			System.out.println(realpath);
			File savefile = new File(new File(realpath),imageFileName);
			System.out.println(savefile);
			System.out.println(savefile.getParentFile());
			if(savefile.getParentFile().exists()){
				try{
					savefile.getParentFile().mkdirs();
					FileUtils.copyFile(image, savefile);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		commodity.setPicsrc(savefile.getPath());
		
		//�־û�
		if(commodityService.addCommodity(commodity))
		{
		//	System.out.println("-----commodityService.add()��success-----");
			return "success";
		}
		else{
			return "error";
		}
	}
	public String showAll()//��ʾ������Ʒ
	{
		if(user != null && user.getId() != 0){
			user = userService.findUser(user.getId());
		}
		if(user == null)
			System.out.println("user �ǿյ�");
		else if(user.getId() == 0)
			System.out.println("user ���ǿյģ�����id = 0");
		commodities = commodityService.waitToPassCommodity("�����");
		if(commodities != null)
		{
			for(int i = 0; i < commodities.size(); i++)
			{
				System.out.println("չʾid��"+commodities.get(i).getId()+" name��"+commodities.get(i).getName());
			}
			System.out.println();
		}
		return "success";
	}
	public String viewDetails()//��ʾ��Ʒϸ��
	{
		user = (User)ActionContext.getContext().getSession().get("user");
		System.out.println("��ʾϸ�ڣ�");
		System.out.println("commodityid:"+commodity.getId());
		//if(msg.equals("��������д��Ʒ����!")){
		//	int id = Integer.parseInt((String)ActionContext.getContext().getSession().get("commid"));
		//	commodity = commodityService.findCommodity(id);
		//}else
		//{
			commodity = commodityService.findCommodity(commodity.getId());
		//}
		
		System.out.println("commodityid:"+commodity.getId());
		leaveMessages = userService.findleaveMessage(commodity);
		System.out.println("commodityid:"+commodity.getId());
		if(user != null && user.getId() != 0){
			System.out.println("userid:"+user.getId());
			user = userService.findUser(user.getId());
		}
		if(user != null)
			System.out.println("user::id:"+user.getId());
		//����
		if(leaveMessages != null)
		{
			System.out.println(leaveMessages.size());
			for(int i = 0; i< leaveMessages.size();i++)
			{
				System.out.println("leaveMessage:"+leaveMessages.get(i).getContact());
				if(leaveMessages.get(i).getUser() == null)
				{
					System.out.print("leaveMessage�������User�ǿյ�");
					User u = new User();
					u.setName("�ο�");
					leaveMessages.get(i).setUser(u);
				}
				else if(leaveMessages.get(i).getUser().getId() == 0)
				{
					System.out.print("leaveMessage�������User���ǿյ�");
					leaveMessages.get(i).getUser().setName("�ο�");
				}
			}
		}
		else{
			System.out.println("leaveMessage is null");
		}
		return "success";
	}
	public String leaveMessage()//����
	{
		user = (User)ActionContext.getContext().getSession().get("user");
		if(user != null && user.getId() != 0)
		{//������û�
			user = userService.findUser(user.getId());
			leaveMessage.setUser(user);
		}
		//System.out.println("����:id:"+user.getId()+"name:"+user.getName());
		commodity = commodityService.findCommodity(commodity.getId());
		//System.out.println("��Ʒ:id:"+commodity.getId()+"name:"+commodity.getName());

		leaveMessage.setCommodity(commodity);


		//System.out.println("leaveMessage"+leaveMessage.getContact()+"user:"+leaveMessage.getUser().getId()+"commodity:"+leaveMessage.getCommodity().getId());
		userService.leaveMessage(leaveMessage);//����
		leaveMessages = userService.findleaveMessage(commodity);
		if(leaveMessages != null)
			for(int i = 0; i < leaveMessages.size(); i++)
			{
				if(leaveMessages.get(i).getUser() == null)
				{
					User u = new User();
					u.setName("�ο�");
					leaveMessages.get(i).setUser(u);
				}
				else if(leaveMessages.get(i).getUser().getId() == 0)
				{
					leaveMessages.get(i).getUser().setName("�ο�");
				}
			}

		return "success";


		
	}
	public String findCommodities(){//��������������Ʒ
		if(commodity != null)
		{
			System.out.println("admincommodity:id:"+commodity.getId()+"name"+commodity.getName());
			if(property.equals("0"))
			{
				commodity.setProperty("");
			}
			else if(property.equals("1"))
			{
				commodity.setProperty("����Ʒ");
			}
			else if(property.equals("2")){
				commodity.setProperty("��ʳ");
			}else if(property.equals("3")){
				commodity.setProperty("���Ӳ�Ʒ");
			}
			commodity.setState("�����");
			commodities = commodityService.findCommodity(commodity);
		}
		return "success";
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Commodity> getCommodities() {
		return commodities;
	}
	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	public List<LeaveMessage> getLeaveMessages() {
		return leaveMessages;
	}
	public void setLeaveMessages(List<LeaveMessage> leaveMessages) {
		this.leaveMessages = leaveMessages;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public CommodityAction(){
		System.out.println("-----creatCommodityAction-----");
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		System.out.println("-----setCommodity-----");
		this.commodity = commodity;
	}
	public ICommodityService getCommodityService() {
		return commodityService;
	}
	public void setCommodityService(ICommodityService commodityService) {
		System.out.println("-----setCommodityService-----");
		this.commodityService = commodityService;
	}
}
