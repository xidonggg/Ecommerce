package com.dao;

import java.util.List;

import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;

public interface ILeaveMessageDAO {
	public boolean save(LeaveMessage leaveMessage);//保存
	public boolean update(LeaveMessage leaveMessage);//更新
	public LeaveMessage find(int id);//查询
	public boolean delete(int id);//删除
	public List<LeaveMessage>findAll();//所有留言
	public List<LeaveMessage>findAll(Commodity commodity);
	public List<LeaveMessage>findAll(User user);
}
