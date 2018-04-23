package com.dao;

import java.util.List;

import com.po.Commodity;
import com.po.LeaveMessage;
import com.po.User;

public interface ILeaveMessageDAO {
	public boolean save(LeaveMessage leaveMessage);//����
	public boolean update(LeaveMessage leaveMessage);//����
	public LeaveMessage find(int id);//��ѯ
	public boolean delete(int id);//ɾ��
	public List<LeaveMessage>findAll();//��������
	public List<LeaveMessage>findAll(Commodity commodity);
	public List<LeaveMessage>findAll(User user);
}
