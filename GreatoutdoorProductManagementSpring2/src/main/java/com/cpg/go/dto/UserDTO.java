package com.cpg.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "UserDTO_GreatOutdoors")
@DynamicUpdate
public class UserDTO {

	@Id
	@Column(name = "User_Id",nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name="User_Name",nullable = false)
	@Pattern(regexp = "[a-z]{1,}[a-z0-9]*",message = "userName InValid")
	private String userName;
	
	@Column(name="User_Password",nullable = false)
	//@Pattern(regexp = "",message="User Password InValid")
	private String userPassword;
	
	@Column(name="User_PhoneNumber",nullable=false)
	@Pattern(regexp="[6-9]{1}\\d{9}",message="User Phone Number InValid")
	private String userPhoneNumber;
	
	@Column(name="User_Email",nullable = false)
	@Pattern(message = "Email Not Valid",regexp = "[a-z_.]+@[a-z]+[.]{1,}[a-z]+")
	private String userEmail;	

	@Column(name="User_Role",nullable=false)
	@Range(min=1,max=3,message="User role InValid")
	private Integer userRole;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userPhoneNumber=" + userPhoneNumber + ", userEmail=" + userEmail + ", userRole=" + userRole + "]";
	}


	
	
	
}
