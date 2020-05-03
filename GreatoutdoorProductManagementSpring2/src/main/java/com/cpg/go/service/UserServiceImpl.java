package com.cpg.go.service;

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

}
