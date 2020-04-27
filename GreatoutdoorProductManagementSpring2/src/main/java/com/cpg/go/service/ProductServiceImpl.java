package com.cpg.go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cpg.go.dao.ProductDAO;
import com.cpg.go.dao.UserDAO;
import com.cpg.go.dto.ProductDTO;
import com.cpg.go.dto.UserDTO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;
	
	@Override
	public ProductDTO getProductById(long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	public boolean addProduct(ProductDTO productDTO) {
	   UserDTO user=new UserDTO();
	   user.setUserId(101);
	   user.setUserName("aravind");
	   user.setUserpassword("123");
	   user.setUserRole(2);
	   user.setUseremail("aravind4532@gmail.com");
	   user.setUserphoneNumber("9866772522");
	   productDTO.setProductMaster(user);
	   productDao.save(productDTO);
	
		return productDao.existsById(productDTO.getProductId());
	}

	@Override
	public boolean editProduct(ProductDTO productDTO) {
		//TODO fetch old record and compare with new record
		//TODO check whether product belongs that particular product master
		productDao.save(productDTO);
		return productDao.existsById(productDTO.getProductId());
	}

	@Override
	public boolean deleteProductById(long id) {
		 productDao.deleteById(id);
		return !productDao.existsById(id);
	}

	@Override
	public List<ProductDTO> getAllProductsOfProductMaster(long id)
	{
		return productDao.findAllProductsByUserId(id);
	}

	@Override
	public Page<ProductDTO> getAllProductsForUser(int pageNumber) {

		Pageable paging=PageRequest.of(pageNumber,5);
		return productDao.findAll(paging);
	}
	
	

}
