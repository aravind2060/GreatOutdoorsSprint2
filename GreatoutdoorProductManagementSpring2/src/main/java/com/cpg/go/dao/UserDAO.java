package com.cpg.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpg.go.dto.UserDTO;

@Repository
public interface UserDAO  extends JpaRepository<UserDTO, Long>{

	public UserDTO findByuserEmail(String userEmail);
	

}
