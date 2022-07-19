package com.ratnesh.rest.webservice.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ratnesh.rest.webservice.user.bean.Address;
import com.ratnesh.rest.webservice.user.bean.MyUser;
import com.ratnesh.rest.webservice.user.bean.User;

@Component
public class UserServiceDAO {
	
	private static List<User> users = new ArrayList<User>();
	
	private int userCount=3;
	
	static {
		users.add(new User(1,"Ram",new Date()));
		users.add(new User(2,"Sita",new Date()));
		users.add(new User(3,"Laxman",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}

	public User save(User user)
	{
		if(user.getId()==null)
		{
			user.setId(++userCount);
		}
		
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
		for(User user : users)
		{
			if(user.getId() == id)
			{
				return user;
			}
		}
		
		return null;
	}
	
	public User deleteById(int id)
	{
		Iterator<User> iterator=users.iterator();
		
		while(iterator.hasNext())
		{
			User user=iterator.next();
			
			if(user.getId() == id)
			{
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
	
	public User saveAddress(int id, Address address)
	{
		for(User user : users)
		{
			if(user.getId() == id)
			{
				if(user.getAddressList()!=null)
					user.getAddressList().add(address);
				else
				{
					List<Address> tempList=new ArrayList<Address>();
					tempList.add(address);
					user.setAddressList(tempList);
				}
				return user;
			}
		}
		
		return null;
	}
	
	public List<Address> findAllAddress(int id){
		for(User user : users)
		{
			if(user.getId() == id)
			{
				if(user.getAddressList()!=null)
					return user.getAddressList();
				//return user;
			}
		}
		
		return null;
	}
}
