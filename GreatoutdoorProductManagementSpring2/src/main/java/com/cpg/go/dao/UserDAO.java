package com.cpg.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpg.go.dto.UserDTO;
/**
 * spring implements implementation for all methods 
 * no transaction or entity manager needs to be maintained by developer whole is managed by spring
 * 
 * @author aravind
 */
@Repository
public interface UserDAO  extends JpaRepository<UserDTO, Long>{

	public UserDTO findByUserName(String userName);

}
