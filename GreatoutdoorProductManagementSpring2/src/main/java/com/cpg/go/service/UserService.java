package com.cpg.go.service;

import java.util.Optional;

import com.cpg.go.dto.UserDTO;

public interface UserService {

	public UserDTO getUserById(Long userId);
	
	public UserDTO signIn(String userName,String userPassword);
	
	public boolean signUp(Optional<Long> adminId,UserDTO newUser);
}
