package com.service.iml;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IUserDAO;
import com.po.User;
import com.service.IUserService;

@Service @Transactional
public class UserService implements IUserService{
	
	@Resource SessionFactory factory;
	private IUserDAO userDAO;
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
	//------------------------------------¹¦ÄÜÇø----------------------------------------
	public boolean save(User user) {
		System.out.println("-----userService.save()¡£in-----");
		try{
			factory.getCurrentSession().persist(user);
			System.out.println("-----userService.save()¡£out-----");
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public void update(User user) {
		factory.getCurrentSession().merge(user);
		
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User find(int userid) {
		return (User)factory.getCurrentSession().get(User.class, userid);
		
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User loginfind(User user) {
		return userDAO.check(user);
	}
	
	public void delete(int userid) {
		factory.getCurrentSession().delete(factory.getCurrentSession().load(User.class, userid));
		
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<User> findAll() {
		return factory.getCurrentSession().createQuery("from user ").list();
	}

	public boolean checkName(String userName) {
		if(this.userDAO.checkName(userName))
			return true;
		else
			return false;
	}
}
