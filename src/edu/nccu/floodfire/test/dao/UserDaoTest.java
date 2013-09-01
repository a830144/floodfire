package edu.nccu.floodfire.test.dao;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.dao.UserDao;
import edu.nccu.floodfire.entity.User;


public class UserDaoTest extends TestCase{
	
	public static void main(String args[]) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("UserDao");

		System.out.println(userDao.isPasswdTrue("Jim", "1234"));

	}
	
	public void testIsPasswdTrue()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("UserDao");
		assertEquals(userDao.isPasswdTrue("Jim", "1234"),false);
	}
	
	public void testGetUserById()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("UserDao");
		System.out.println(userDao.getUserById("Jim"));
	}
	
	public void testGetUserByIdWildCard()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("UserDao");
		@SuppressWarnings("rawtypes")
		List list = userDao.getUserByIdWildCard("on");
		for(int i=0;i<list.size();i++)
		{
			User user = (User)list.get(i);
			System.out.println(user.getIduser() +"::"+user.getPassword());
		}
	}
	
	public void testAddUser()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao)applicationContext.getBean("UserDao");
		userDao.addUser("funcan", "1234");
	}
}
