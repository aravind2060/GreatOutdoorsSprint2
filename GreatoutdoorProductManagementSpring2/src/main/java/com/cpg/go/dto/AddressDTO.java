package com.cpg.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="AddressDTO_GreatOutdoors")
@DynamicUpdate
public class AddressDTO {

   	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Address_Id",nullable=false)
   	@Range(min=1,max=Long.MAX_VALUE,message="Address Id InValid")
   	private Long addressId;
   	
	@NotBlank(message = "Address Building Number cannot be empty")
   	@Column(name = "Address_Building_Number",nullable=false)
	private String addressBuildingNo;
   	
 
	@NotBlank(message = "Address City cannot be empty")
   	@Column(name="Address_City",nullable=false)
	private String addressCity;
   	
 
	@NotBlank(message = "Address State cannot be empty")
   	@Column(name="Address_State",nullable=false)
	private String addressState;
	
	@NotBlank(message = "Address Field cannot be empty")
   	@Column(name="Address_Field",nullable=false)
   	private String addressField;
   	
	@NotBlank(message = "Address Zip cannot be empty")
   	@Column(name="Address_Zip",nullable=false)
	private String addressZip;
	
	
	@ManyToOne(targetEntity = UserDTO.class)
	@JoinColumn(name = "User_Id",nullable = false)
	private UserDTO user;
	
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getAddressBuildingNo() {
		return addressBuildingNo;
	}
	public void setAddressBuildingNo(String buildingNo) {
		this.addressBuildingNo = buildingNo;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String city) {
		this.addressCity = city;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String state) {
		this.addressState = state;
	}
	public String getAddressField() {
		return addressField;
	}
	public void setAddressField(String field) {
		this.addressField = field;
	}
	public String getAddressZip() {
		return addressZip;
	}
	public void setAddressZip(String zip) {
		this.addressZip = zip;
	}
	
	
	
}
