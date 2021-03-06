package com.cpg.go.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cpg.go.dao.ProductDAO;
import com.cpg.go.dto.ProductDTO;
import com.cpg.go.dto.UserDTO;
/**
 * {@link ProductServiceImpl} is a service class which consist of add,read,delete,search,update products
 * @author aravind
 *
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public ProductDTO getProductById(Long productId) {
		return productDao.findById(productId).orElse(null);
	}

	@Override
	public boolean addProduct(ProductDTO productDTO,Long productMasterId) {
	   UserDTO user=userService.getUserById(productMasterId);
		if(user!=null)
		{
		   if(user.getUserRole()==2)
		   {
			   productDTO.setProductMaster(user);
			   productDao.save(productDTO);
			return productDao.existsById(productDTO.getProductId());  
		   }
		   else
			   return false;
		}
		else
		{
			return false;
		}
	   
	}

	@Override
	public boolean updateProduct(ProductDTO productDTO,Long productMasterId) {
		UserDTO user=userService.getUserById(productMasterId);
		if(user!=null)
		{
		   if(user.getUserRole()==2)
		   {
			  ProductDTO p=getProductById(productDTO.getProductId());
			 if(p.getProductMaster().getUserId()==productMasterId)
			 {
				 productDTO.setProductMaster(user);
				   productDao.save(productDTO);
				return productDao.existsById(productDTO.getProductId());  
			 }
			 else
			 {
				 return false;
			 }
		   }
		   else
			   return false;
		}
		else
		{
			return false;
		}
	}

	
	@Override
	public boolean deleteProductById(Long productId,Long productMasterId) {
		UserDTO user=userService.getUserById(productMasterId);
		if(user!=null)
		{
		   if(user.getUserRole()==2)
		   {
			  ProductDTO p=getProductById(productId);
			 if(p.getProductMaster().getUserId()==productMasterId)
			 {
				    productDao.deleteById(productId);
					return !productDao.existsById(productId);  
			 }
			 else
			 {
				 return false;
			 }
		   }
		   else
			   return false;
		}
		else
		{
			return false;
		}
		 
	}
	
	@Override
	public Page<ProductDTO> getAllProductsOfProductMaster(Long productMasterId,int pageNumber)
	{
		
		UserDTO user=userService.getUserById(productMasterId);
		if(user!=null)
		{
			if(user.getUserRole()==2)
			{
				Pageable paging=PageRequest.of(pageNumber,5,Sort.by(Direction.ASC, "productId"));
				return productDao.findAllProductsByUserId(productMasterId,paging); 	
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	/**
	 * Method Name : getProductsOfUser <br/>
	 * Description : product's will be returned ,Pagination has been implemented   <br/>
	 * @param userId 
	 * @param pageNumber
	 * @return List of products will be returned if userid and pagenumber is valid
	 * 
	 */
	@Override
	public Page<ProductDTO> getAllProductsForUser(Long userId,int pageNumber) {
		UserDTO user=userService.getUserById(userId);
		if(user!=null)
		{
			  Pageable paging=PageRequest.of(pageNumber,5,Sort.by(Direction.ASC, "productId"));
				return productDao.findAll(paging);  
		}
		else
		{
			return null;
		}
	}

	/**
	 * Method Name : searchProduct <br/>
	 * Description : search's products which starts with given letter or word   and with pagenumber<br/>
	 * @param searchKeyword
	 * @param pageNumber
	 * @return List of products will be returned if searchkeyword is valid and pagenumber is valid
	 * 
	 */
   @Override	
   public Page<ProductDTO> searchProduct(String searchKeyword,int pageNumber)
   {
	  
	   return productDao.searchProducts(searchKeyword,PageRequest.of(pageNumber,5,Sort.by(Direction.ASC, "productId")));
   }
   
   
}
