package com.ajayjava.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
private static List<User> users=new ArrayList<>();
	private static int usersCount=5;

	
	public List<User >  findAll()
	{
		return users;
	}
	
	public User save(User user) 
	
	
	{
		if (user.getId()==null)
		{
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
		for (User user :users)
		{
			if (user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}
	
	public User deletebyId(int id)
	{
		Iterator<User> iterator =users.iterator();
		
		while(iterator.hasNext())
		{
			User user=iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
}
		
	}
	
