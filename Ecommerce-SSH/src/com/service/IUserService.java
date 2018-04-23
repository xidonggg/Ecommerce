package com.service;

import java.util.List;

import com.po.Commodity;
import com.po.User;
import com.po.LeaveMessage;

public interface IUserService {
	public User loginfind(User user);//��½���
	public boolean updateUser(User user);//�޸��û���Ϣ
	public boolean deleteUser(User user);//ɾ���û�
	public boolean registerUser(User user);//�����û�
	public User findUser(int id);//����id��ѯ�û�
	public boolean updateScore(User user,int score);//���ӻ���
	public boolean leaveMessage(LeaveMessage l);//��������
	public List<LeaveMessage> findleaveMessage(Commodity commodity);//������Ʒ��������
	public List<LeaveMessage> findAllLeaveMessage();//������������
	public LeaveMessage findLeaveMessage(LeaveMessage l);//��������
	public boolean deleteLeaveMessage(LeaveMessage l);//ɾ������
	public List<LeaveMessage> findleaveMessageByUaC(User user,Commodity commodity);//�����û�����Ʒ��������
	public List<LeaveMessage> findLeaveMessage(List<LeaveMessage> list);
	public String getMsg();
	
	public List<User> findAllUser();//��ѯ�����û�
	public List<User> findUser(User user);//������ѯ�û�
}
