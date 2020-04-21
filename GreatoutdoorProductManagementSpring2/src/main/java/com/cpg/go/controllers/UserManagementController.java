package com.cpg.go.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.go.dao.UserDAO;
import com.cpg.go.dto.UserDTO;

@RestController
@CrossOrigin
public class UserManagementController {

	@Autowired
	UserDAO userDao;
	
	@PostMapping(value="/signin",consumes = {"application/json"})
	public ResponseEntity<Object> signin(@RequestAttribute(name = "user_email",required = true) String userEmail,@RequestAttribute(name="user_password",required = true) String userPassword)
	{
		 UserDTO user=userDao.findByuserEmail(userEmail);
		 if(user==null)
		 {
			 return new ResponseEntity<>(new HashMap<>().put("Email", "Email Id Doesn't Exist"),HttpStatus.BAD_REQUEST);
		 }
		 else
		 {
			 if(user.getUserpassword().contentEquals(userPassword))
			 {
				 
				return new ResponseEntity<>("",HttpStatus.OK); 
			 }
			 else
			 {
				 return new ResponseEntity<>(new HashMap<>().put("Password", "Password is inCorrect"),HttpStatus.BAD_REQUEST);
			 }
		 }
	}
	
	@RequestMapping(value="getuser/{id}")
	public UserDTO getUser(@PathVariable("id") long id)
	{
		return userDao.findById(id).orElse(null);
	}
}
