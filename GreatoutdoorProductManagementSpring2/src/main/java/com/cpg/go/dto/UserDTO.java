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
	
	@NotBlank(message = "User Name cannot be empty")
	@Column(name="User_Name",unique=true,nullable = false)
	@Size(min = 2,max=25,message="User Name size is not in range")
	private String userName;
	
	@NotBlank(message = "User Password cannot be empty")
	@Column(name="User_Password",nullable = false)
	@Pattern(regexp = "",message="User Password InValid")
	private String userPassword;
	
	@NotBlank(message = "User Phone Number cannot be empty")
	@Column(name="User_PhoneNumber",nullable=false)
	@Pattern(regexp="",message="User Phone Number InValid")
	private String userPhoneNumber;
	
	@NotBlank(message = "User Email cannot be empty")
	@Column(name="User_Email",unique = true,nullable = false)
	@Email(message = "Email Not Valid")
	private String userEmail;
	
	//TODO Check from where role is getting created
	@Column(name="User_Role")
	@Range(min=1,max=3,message="User role InValid")
	@NotNull(message="User Role cannot be empty")
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

	public String getUserpassword() {
		return userPassword;
	}

	public void setUserpassword(String userpassword) {
		this.userPassword = userpassword;
	}

	public String getUserphoneNumber() {
		return userPhoneNumber;
	}

	public void setUserphoneNumber(String userphoneNumber) {
		this.userPhoneNumber = userphoneNumber;
	}

	public String getUseremail() {
		return userEmail;
	}

	public void setUseremail(String useremail) {
		this.userEmail = useremail;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int role) {
		this.userRole = role;
	}
	
	
    	
	
	
}
