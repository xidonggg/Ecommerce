package com.service.impl;

import java.util.List;

//import org.springframework.stereotype.Service;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICommodityDAO;
import com.dao.ILeaveMessageDAO;
import com.dao.IUserDAO;
import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;
import com.service.IUserService;
import com.tools.extractList;

//@Service 
public class UserService implements IUserService{
	@Resource SessionFactory factory;
	private IUserDAO userDAO;
	private ILeaveMessageDAO leaveMessageDAO;
	private ICommodityDAO commodityDAO;
	private String msg = "";
	public UserService()
	{
		System.out.println("create UserService");
	}
	public void setUserDAO(IUserDAO userDAOinService)
	{
		System.out.println("-----setUserDAO-----");
		this.userDAO = userDAOinService;
	}
	public IUserDAO getUserdao(){
		return userDAO;
	}
	//------------------------------------功能区----------------------------------------
	
	@Override
	public User loginfind(User user) {
		return userDAO.check(user);
	}
	
	@Override
	public boolean updateUser(User user) {
		if(userDAO.update(user))
			return true;
		return false;
	}
	@Override
	public boolean deleteUser(User user) {
		if(userDAO.delete(user))
		{
			System.out.println("deleteUser success");
			return true;
		}
		System.out.println("deleteUser fail");
		return false;
	}
	@Override
	@Transactional
	public boolean registerUser(User user) {//注册用户
		
		if(userDAO.checkName(user.getName()))
		{
			if(userDAO.save(user))
				return true;
			else
				return false;
		}
		else
		{
			msg = "该用户名已被注册";
			return false;
		}

	}
	@Override
	public User findUser(int id) {
		return userDAO.find(id);
	}
	@Override
	public List<User> findAllUser() {//查找所有用户
		return userDAO.findAll();
	}
	@Override
	public List<User> findUser(User user) {//根据信息查找用户
		
		extractList list = new extractList();
		//List<User> result = new ArrayList<User>();
		List<User> u,u2;
		if(user.getName().equals(""))
		{
			u = userDAO.findAll();
		}
		else{
			u = userDAO.findAllbyName(user.getName());
			
		}
		if(user.getAddress().equals(""))
		{
			System.out.println("^^^^^^^^^^^address是空的");
			u2 = userDAO.findAll();
		}else{
			System.out.println("^^^^^^^^^^^"+user.getAddress());
			u2 = userDAO.findAllbyAddress(user.getAddress());
		}		
		//合并
		@SuppressWarnings("static-access")
		List<User> result = list.getExtractlistUser(u, u2);
		if(result != null)
		{
			for(int i = 0; i < result.size(); i++)
			{
				System.out.println("result:id:"+result.get(i).getId()+" name:"+result.get(i).getName());
			}
		}
		return result;
	}
	@Override
	@Transactional
	public List<LeaveMessage> findleaveMessage(Commodity commodity) {
		System.out.println("leaveMessage:commocdityid:"+commodity.getId());
		List<LeaveMessage>list = leaveMessageDAO.findAll(commodity);
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getUser() != null && list.get(i).getUser().getId() != 0)
			{
				User u = userDAO.find(list.get(i).getUser().getId());
				list.get(i).setUser(u);
			}
		}
		return list;
	}
	@Override
	public boolean leaveMessage(LeaveMessage l) {
		return leaveMessageDAO.save(l);
	}
	public ILeaveMessageDAO getLeaveMessageDAO() {
		return leaveMessageDAO;
	}
	public void setLeaveMessageDAO(ILeaveMessageDAO leaveMessageDAO) {
		this.leaveMessageDAO = leaveMessageDAO;
	}
	@Override
	public boolean updateScore(User user,int score) {
		user.setAccScore(score);
		return userDAO.update(user);
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ICommodityDAO getCommodityDAO() {
		return commodityDAO;
	}
	public void setCommodityDAO(ICommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}
	@Override
	public List<LeaveMessage> findAllLeaveMessage() {
		List<LeaveMessage> lm = leaveMessageDAO.findAll();
		if(lm != null)
		{
			for(int i = 0; i < lm.size(); i++)
			{
				if(lm.get(i).getUser() != null)
				{
					User u = userDAO.find(lm.get(i).getUser().getId());
					lm.get(i).setUser(u);
				}
				Commodity c = commodityDAO.find(lm.get(i).getCommodity().getId());
				lm.get(i).setCommodity(c);
			}
		}
		
		return lm;
	}
	@Override
	public LeaveMessage findLeaveMessage(LeaveMessage l) {
		return leaveMessageDAO.find(l.getId());
	}
	@Override
	public boolean deleteLeaveMessage(LeaveMessage l) {
		if(leaveMessageDAO.delete(l.getId()))
			return true;
		else
			return false;
	}
	@Override
	public List<LeaveMessage> findleaveMessageByUaC(User user,Commodity commodity) {

		extractList list = new extractList();
		List<LeaveMessage> u,u2;
		if(user == null || user.getId() == 0)
		{
			u = leaveMessageDAO.findAll();
		}
		else{
			u = leaveMessageDAO.findAll(user);
			
		}
		if(commodity == null || commodity.getId() == 0)
		{
			u2 = leaveMessageDAO.findAll();
		}else{
			System.out.println("^^^^^^^^^^^"+user.getAddress());
			u2 = leaveMessageDAO.findAll(commodity);
		}		
		//合并
		@SuppressWarnings("static-access")
		List<LeaveMessage> result = list.getExtractlistLeaveMessage(u, u2);
		return result;
	}
	@Override
	public List<LeaveMessage> findLeaveMessage(List<LeaveMessage> list) {
		if(list != null)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getUser() != null)
				{
					User u = userDAO.find(list.get(i).getUser().getId());
					list.get(i).setUser(u);
				}
				Commodity c = commodityDAO.find(list.get(i).getCommodity().getId());
				list.get(i).setCommodity(c);
			}
		}
		
		return list;
	}

	

}
