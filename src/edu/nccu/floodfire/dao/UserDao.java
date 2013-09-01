package edu.nccu.floodfire.dao;

import java.util.List;

import edu.nccu.floodfire.entity.User;

public interface UserDao {
	public boolean isPasswdTrue(String userId,String password);
	public void addUser(String userName,String password);
	public User getUserById(String id);
	public List<User> getUserByIdWildCard(String id);
	
}
