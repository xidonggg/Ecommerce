package com.service;


import com.po.Admin;

public interface IAdminService {
	public boolean registerAdmin(Admin admin);//ע��
	public boolean updateAdmin(Admin admin);//�޸���Ϣ
	public boolean deleteAdmin(int id);//ɾ������Ա
	public String getMsg();
	public Admin loginAdmin(Admin admin);//��½
	public Admin find(int id);//����

	//����������Ϣ
	//public List<>
	
}
