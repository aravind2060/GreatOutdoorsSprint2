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

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="AddressDTO_GreatOutdoors")
public class AddressDTO {

   	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Address_Id")
   	@Range(min=1,message="Address Id InValid")
   	private long addressId;
   	
   	@NotBlank(message="Address BuildingNumber InValid")
   	@Column(name = "Address_Building_Number")
	private String buildingNo;
   	
   	@NotBlank(message="Address City InValid")
   	@Column(name="Address_City")
	private String city;
   	
   	@NotBlank(message="Address State InValid")
   	@Column(name="Address_State")
	private String state;
	
   	@NotBlank(message="Address field InValid")
   	@Column(name="Address_Field")
   	private String field;
   	
   	@NotBlank(message="Address Zip InValid")
   	@Column(name="Address_Zip")
	private String zip;
	
	
	@ManyToOne(targetEntity = UserDTO.class)
	@JoinColumn(name = "User_Id")
	private UserDTO user;
	
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	
}
