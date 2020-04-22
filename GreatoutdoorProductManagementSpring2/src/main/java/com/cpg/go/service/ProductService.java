package com.cpg.go.service;

import java.util.List;

import com.cpg.go.dto.ProductDTO;

public interface ProductService {

	public ProductDTO getProductById(long id);
	public boolean addProduct(ProductDTO productDTO);
	public boolean editProduct(ProductDTO productDTO);
	public boolean deleteProductById(long id);
	public  List<ProductDTO> getAllProductsOfProductMaster(long id);
}
