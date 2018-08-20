package br.com.pedrofrohmut.shareposts3.service;

import br.com.pedrofrohmut.shareposts3.dao.UserDao;
import br.com.pedrofrohmut.shareposts3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	private final UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	public boolean create(User user)
	{
		// TODO: implement this method + interaction with DB
		return true;
	}
}
