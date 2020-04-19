package com.cpg.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "ProductDTO_GreatOutdoors")
@DynamicUpdate
public class ProductDTO {
  
	@Id
	@Column(name = "Product_Id")
	@Range(min=1,message="Product Id InValid")
	private long productId;
	
	@Column(name="Product_Price")
	@Range(min=1,message="Product Price is InValid")
	private double price;
	
	@NotBlank(message = "Product Color Cannot be empty")
	@Column(name="Product_Colour")
	private String colour;
	
	@NotBlank(message = "Product Dimension Cannot be empty")
	@Column(name="Product_Dimension")
	@Size(min = 2,max=20,message = "Product Dimension size not valid")
	private String dimension;
	
	@NotBlank(message = "Product Specification Cannot be empty")
	@Column(name="Product_Specification")
	private String specification;
	
	@NotBlank(message = "Product Manufacturer Cannot be empty")
	@Column(name="Product_Manufacturer")
	private String manufacturer;
    
	@Column(name="Product_Quantity")
	@Range(min=1,message="Product Quantity InValid")
	private int quantity;
	
	@Column(name="Product_Category")
	@Range(min = 1,max=5,message = "Product Category InValid")
	private int productCategory;
	
	@NotBlank(message = "Product Name Cannot be empty")
	@Column(name="Product_Name")
	private String productName;
	
	@NotBlank(message = "Product Brand Cannot be empty")
	@Column(name="Product_Brand")
	@Pattern(regexp = "[0-9]*",message="not valid pattern")
	@Size(min=3,max=5,message="Product Brand Size is not valid")
	private String productBrand;

	@ManyToOne(targetEntity = UserDTO.class)
	@JoinColumn(name = "ProductMaster_Id")
	private UserDTO productMaster;
	
	public UserDTO getProductMaster() {
		return productMaster;
	}

	public void setProductMaster(UserDTO productMaster) {
		this.productMaster = productMaster;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	
}
