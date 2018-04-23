package com.dao;

import java.util.List;

import com.po.User;

public interface IUserDAO {
	public boolean save(User user);//保存
	public boolean update(User user);//更新
	public User find(int id);//查询
	public boolean delete(User user);//删除
	public List<User>findAll();//所有用户
	public boolean checkName(String userName);//检查名字是否已经存在
	public User check(User user);//用户登录检查
	public List<User>findAllbyAddress(String address);//按地址查询
	public List<User>findAllbyName(String name);//按姓名查询
}
