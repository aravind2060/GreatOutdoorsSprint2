package com.cpg.go.service;


import org.springframework.data.domain.Page;

import com.cpg.go.dto.ProductDTO;

public interface ProductService {

	public ProductDTO getProductById(Long productId);
	public boolean addProduct(ProductDTO productDTO,Long productMasterId);
	public boolean updateProduct(ProductDTO productDTO,Long productMasterId);
	public boolean deleteProductById(Long productId,Long productMasterId);
	public Page<ProductDTO> getAllProductsOfProductMaster(Long productMasterId,int pageNumber);
	public Page<ProductDTO> getAllProductsForUser(Long userId,int pageNumber);
	public Page<ProductDTO> searchProduct(String searchKeyWord,int pageNumber);
	

}
