package com.cpg.go.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.go.dao.UserDAO;
import com.cpg.go.dto.UserDTO;
import com.cpg.go.exceptions.LoginException;
import com.cpg.go.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/user")
public class UserManagementController {

	@Autowired
	UserService userService;
	
	@GetMapping(value="/signin/{userName}/{userPassword}",produces = {"application/json"})
	public ResponseEntity<Object> signin(@PathVariable("userName") String userName,@PathVariable("userPassword") String userPassword)
	{
	    UserDTO user=userService.signIn(userName, userPassword);
	    if(user!=null)
	    	return new ResponseEntity<>(user,HttpStatus.OK);
	    else
	    	return new ResponseEntity<>("Signin failed",HttpStatus.BAD_REQUEST);
	}
	
	
	public UserDTO getUser(@PathVariable("id") long userId)
	{
		return userService.getUserById(userId);
	}
	
	@PostMapping(value = "/signup/{adminUserId}")
	public ResponseEntity<Object> signUp(@PathVariable("adminUserId")Optional<Long> adminUserId,@Valid @RequestBody UserDTO newUser,BindingResult bindingResult)
	{
		System.out.println(newUser);
		if(bindingResult.hasErrors())
		{
			Map<String,String> list=new HashMap<>();
			bindingResult.getAllErrors().forEach(error->{
				
				list.put(""+((FieldError)error).getField(),error.getCode()+":"+error.getDefaultMessage());
			});
			throw new LoginException(list);
		}
	     if(userService.signUp(adminUserId,newUser))
	     {
	          return new ResponseEntity<>("SignUp Success!",HttpStatus.OK);	 
	     }
	     else
	     {
	    	 return new ResponseEntity<>("SignUp failed",HttpStatus.BAD_REQUEST);
	     }
	}
}
