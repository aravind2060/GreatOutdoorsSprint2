package com.cpg.go.controllers;

import java.util.HashMap;
import java.util.Optional;

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
import com.cpg.go.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserManagementController {

	@Autowired
	UserService userService;
	
//	@PostMapping(value="/signin/{userName}/{userPassword}",consumes = {"application/json"})
//	public ResponseEntity<Object> signin(@RequestAttribute(name = "userName",required = true) String userName,@RequestAttribute(name="userPassword",required = true) String userPassword)
//	{
//		 UserDTO user=userService.findByuserEmail(userEmail);
//		 if(user==null)
//		 {
//			 return new ResponseEntity<>(new HashMap<>().put("Email", "Email Id Doesn't Exist"),HttpStatus.BAD_REQUEST);
//		 }
//		 else
//		 {
//			 if(user.getUserpassword().contentEquals(userPassword))
//			 {
//				 
//				return new ResponseEntity<>("",HttpStatus.OK); 
//			 }
//			 else
//			 {
//				 return new ResponseEntity<>(new HashMap<>().put("Password", "Password is inCorrect"),HttpStatus.BAD_REQUEST);
//			 }
//		 }
//	}
//	
	
	public UserDTO getUser(@PathVariable("id") long userId)
	{
		return userService.getUserById(userId);
	}
	
//	@PostMapping(value = "/signup/{userId}")
//	public ResponseEntity<Object> signUp(@PathVariable("userId")Optional<String> userId)
//	{
//		
//	}
}
