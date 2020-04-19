package com.cpg.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "UserDTO_GreatOutdoors")
public class UserDTO {

	@Id
	@Column(name = "User_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=1,message="User Id InValid")
	private long userId;
	
	@NotBlank(message="User name InValid")
	@Column(name="User_Name")
	@Size(min = 2,max=25,message="User Name size is not in range")
	private String userName;
	
	@NotBlank(message="User Password InValid")
	@Column(name="User_Password")
	@Pattern(regexp = "",message="User Password InValid")
	private String userpassword;
	
	@NotBlank(message="User PhoneNumber InValid")
	@Column(name="User_PhoneNumber")
	@Pattern(regexp="",message="User Phone Number InValid")
	private String userphoneNumber;
	
	@NotBlank(message="User Email InValid")
	@Column(name="User_Email",unique = true)
	@Pattern(regexp="",message="User Email InValid")
	private String useremail;
	
	//TODO Check from where role is getting created
	@Column(name="User_Role")
	@Range(min=1,max=2,message="User role InValid")
	private int role;

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
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserphoneNumber() {
		return userphoneNumber;
	}

	public void setUserphoneNumber(String userphoneNumber) {
		this.userphoneNumber = userphoneNumber;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
    	
	
	
}
