package com.cpg.go.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpg.go.dto.ProductDTO;

@Repository
public interface ProductDAO  extends JpaRepository<ProductDTO,Long>{

	@Query("SELECT p FROM ProductDTO p where p.productMaster.userId=:id")
	public Page<ProductDTO> findAllProductsByUserId( long id,Pageable pageable);
	
	@Query("SELECT p FROM ProductDTO p where p.productName LIKE :searchKeyword% OR p.productBrand LIKE :searchKeyword%")
	public Page<ProductDTO> searchProducts(String searchKeyword,Pageable pageable);
	
	

}

