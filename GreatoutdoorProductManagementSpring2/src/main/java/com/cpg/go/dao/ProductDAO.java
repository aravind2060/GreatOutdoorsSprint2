package com.cpg.go.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cpg.go.dto.ProductDTO;

@Repository
public interface ProductDAO  extends JpaRepository<ProductDTO,Long>{

	@Query("SELECT p FROM ProductDTO p where p.productMaster.userId=:id")
	public List<ProductDTO> findAllProductsByUserId( long id);
}

