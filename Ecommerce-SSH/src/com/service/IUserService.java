package com.service;

import java.util.List;

import com.po.Commodity;
import com.po.User;
import com.po.LeaveMessage;

public interface IUserService {
	public User loginfind(User user);//登陆检查
	public boolean updateUser(User user);//修改用户信息
	public boolean deleteUser(User user);//删除用户
	public boolean registerUser(User user);//增加用户
	public User findUser(int id);//根据id查询用户
	public boolean updateScore(User user,int score);//增加积分
	public boolean leaveMessage(LeaveMessage l);//增加留言
	public List<LeaveMessage> findleaveMessage(Commodity commodity);//根据商品查找留言
	public List<LeaveMessage> findAllLeaveMessage();//查找所有留言
	public LeaveMessage findLeaveMessage(LeaveMessage l);//查找留言
	public boolean deleteLeaveMessage(LeaveMessage l);//删除留言
	public List<LeaveMessage> findleaveMessageByUaC(User user,Commodity commodity);//根据用户和商品查找留言
	public List<LeaveMessage> findLeaveMessage(List<LeaveMessage> list);
	public String getMsg();
	
	public List<User> findAllUser();//查询所有用户
	public List<User> findUser(User user);//条件查询用户
}
