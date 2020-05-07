package com.cpg.go.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.go.dao.UserDAO;
import com.cpg.go.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public UserDTO getUserById(Long userId) {
		if(userId!=null && userId>0) 
		{
		 return userDao.findById(userId).orElse(null);	
		}
		else
		{
			return null;
		}
	}

	@Override
	public UserDTO signIn(String userName, String userPassword) {
		UserDTO user=userDao.findByUserName(userName);
		if(user!=null && user.getUserName().contentEquals(userName) && user.getUserPassword().contentEquals(userPassword))
		{
			return user;
		}
		else
		   return null;
	}

	@Override
	public boolean signUp(Optional<Long> adminUserId, UserDTO newUser) {
		
		if(adminUserId!=null && adminUserId.orElse(0l)>0)
		{
			UserDTO user=getUserById(adminUserId.orElse(0l));
		  	if(user!=null && user.getUserRole()==1)
		  	{
		  		if(newUser!=null)
		  		{
		  		//Check newUser already exist
			  		if(userDao.findByUserName(newUser.getUserName())!=null)
			  		{
			  			return false;
			  		}
			  		else
			  		{
			  		 newUser.setUserRole(2);
			  		 userDao.save(newUser);
			  		    return true;
			  		}	
		  		}
		  		else
		  			return false;
		  		
		  	}
		  	else
		  		return false;
		}
		else
		{
			if(newUser!=null)
			{
				if(userDao.findByUserName(newUser.getUserName())!=null)
					return false;
				else
				{
					newUser.setUserRole(3);
					userDao.save(newUser);
					return true;
				}
			}
			else
				return false;
		}
	}

}
