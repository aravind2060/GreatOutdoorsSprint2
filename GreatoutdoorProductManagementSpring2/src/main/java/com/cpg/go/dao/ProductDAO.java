package com.cpg.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpg.go.dto.ProductDTO;

@Repository
public interface ProductDAO  extends JpaRepository<ProductDTO,Long>{

}

