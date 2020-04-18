package com.cpg.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.go.dao.ProductDAO;
import com.cpg.go.dto.ProductDTO;

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
		
	   productDao.save(productDTO);
	
		return productDao.existsById(productDTO.getProductId());
	}

	@Override
	public boolean editProduct(ProductDTO productDTO) {
		//TODO fetch old record and compare with new record
		productDao.save(productDTO);
		return productDao.existsById(productDTO.getProductId());
	}

	@Override
	public boolean deleteProductById(long id) {
		 productDao.deleteById(id);
		return !productDao.existsById(id);
	}
	
	

}
