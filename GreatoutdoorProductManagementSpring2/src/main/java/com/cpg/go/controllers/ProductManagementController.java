package com.cpg.go.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.go.dto.ProductDTO;
import com.cpg.go.dto.QueryResponseDTO;
import com.cpg.go.exceptions.ProductException;
import com.cpg.go.service.ProductServiceImpl;


/**
 * {@link ProductManagementController} is RestController class which consist
 *  add,update,delete,read products
 * *created on:21-APR-2020 
 * @author aravind 
 * 
 *  
 */
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/product")
public class ProductManagementController {

	
 
	@Autowired
	ProductServiceImpl productService;
	
	/**
	 * Method: addProduct
	 * Description:ProductMaster can able to add products.
	 * @param productMasterId only valid productMaster can add products
	 * @param productDTO product which he wants to add
	 * @param bindingResult is default interface of spring which validates input without moving to service
	 * @throws ProductException
     * @returns  {@link ProductDTO} which consist message and {@link HttpStatus}}
	 * @author aravind
	 * *created on:21-APR-2020
	 */
	
	//TODO add session
	@PostMapping(value = "/addproduct/{productMasterId}",consumes = {"application/json","application/xml"})
	public ResponseEntity<Object> addProduct(@PathVariable("productMasterId") long productMasterId, @Valid @RequestBody ProductDTO productDTO,BindingResult bindingResult)
	{
		
		if(bindingResult.hasErrors())
		{
		
		  Map<String,Map<String,String>> map=new HashMap<>();
		  
		  bindingResult.getAllErrors().forEach(error->{
			  
			    String key=((FieldError)error).getField();
			  
			    if(map.containsKey(key))
			   {
				   Map<String,String> m=map.get(key);
				   m.put(error.getCode(), error.getDefaultMessage());
				   map.put(key,m);
			   }
			   else
			   {  
				   Map<String,String> m=new HashMap<>();
				   m.put(error.getCode(), error.getDefaultMessage());
				   map.put(key,m);
			   }
		  });
		  
		  throw new ProductException(map);
		}
		else if(productDTO.getProductId()!=null && productService.getProductById(productDTO.getProductId())!=null)
		{
			HashMap<String,Map<String,String>> map=new HashMap<>();
			HashMap<String,String> m=new HashMap<>();
			m.put("NotUnique", "Product with this id already exist");
			map.put("productId", m);
			throw new ProductException(map);
		}
		else
		{
			if(productService.addProduct(productDTO,productMasterId))
			{
			 return new ResponseEntity<>("product Added Successfully!",HttpStatus.OK);
			}
			 else
				return new ResponseEntity<>("Unable to add product",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Method Name : getProductById<br/>
	 * Description :ProductMaster can able to get {@link ProductDTO}.<br/>
	 * fetches products if product belongs to that productmaster otherwise returns message<br/>
	 * @param productMasterId only valid productMaster can get {@link ProductDTO}
	 * @returns {@link ProductDTO} which consist message and {@link HttpStatus}}
	 * @author aravind
	 * *created on:21-APR-2020
	 */
	@GetMapping(value="/getproductbyid/{productId}",produces = {"application/json","application/xml"})
	public ResponseEntity<Object> getProductById(@PathVariable("productId") long productId)
	{
		if(productId>0)
		{
		  ProductDTO product=productService.getProductById(productId);
		  
		  if(product==null)
		  {
			return new ResponseEntity<>("Product Doesn't exist",HttpStatus.BAD_REQUEST);
		  }
		  else
		  {
			return new ResponseEntity<>(product,HttpStatus.OK);
		  }
		}
		else
		{
			return new ResponseEntity<>("Product Id InValid",HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Method Name : deleteProductById<br/>
	 * Description :ProductMaster can able to delete products.<br/>
	 * delete products if product belongs to that productmaster otherwise returns message<br/>
	 * @param productMasterId only valid productMaster can delete products
	 * @param productId product which he wants to delete
	 * @returns Success or failure which consist message and {@link HttpStatus}}
	 * @author aravind
	 * *created on:21-APR-2020
	 */
	@DeleteMapping(value="/deleteproduct/{productMasterId}/{productid}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("productMasterId") long productMasterId, @PathVariable("productid") long productId)
	{
		if(productId>0)
		{
			if(productService.getProductById(productId)!=null)
			{
			  if(productService.deleteProductById(productId,productMasterId))
				  return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.NO_CONTENT);
			  else
				  return new ResponseEntity<>("Either specified id doesn't exist or problem encountered while deleting",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else
			{
				return new ResponseEntity<>("No Such Product Exist to Delete",HttpStatus.BAD_REQUEST);
			}
		}else
		{
			return new ResponseEntity<>("Product Id InValid",HttpStatus.BAD_REQUEST);
		}
		
	}
	/**
	 * Method Name : updateProduct<br/>
	 * Description :ProductMaster can able to update product.<br/>
	 * update product if product belongs to that productmaster otherwise returns message<br/>
	 * @param productMasterId only valid productMaster can update product
	 * @param {@link ProductDTO} product which he wants to update
	 * @returns Success or failure which consist message and {@link HttpStatus}}
	 * @author aravind
	 * *created on:21-APR-2020
	 */
	@PutMapping(value="/updateproduct/{productMasterId}",consumes= {"application/json","application/xml"})
	public ResponseEntity<Object> updateProduct(@PathVariable("productMasterId") Long productMasterId,@RequestBody ProductDTO productDTO)
	{
		if(productDTO!=null && productDTO.getProductId()!=null && productService.getProductById(productDTO.getProductId())!=null)
		{
			if(productService.updateProduct(productDTO,productMasterId))
				return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
			else
				return new ResponseEntity<>("Either Specified product doesnot belongs to you or some problem encountered  while updating",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity<>("Provide Valid Entity",HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Method Name : getProductsOfProductMaster <br/>
	 * Description :                  <br/>
	 * @param productMasterId
	 * @param pageNumber
	 * @return
	 */
	@GetMapping(value="/getproductsofproductmaster/{productMasterId}/{pageNumber}",produces= {"application/json"})
	public ResponseEntity<Object> getProductsOfProductMaster(@PathVariable("productMasterId") long productMasterId,@PathVariable("pageNumber") int pageNumber)
	{
	    if(productMasterId>0 && pageNumber>=0)
	    {
	      Page<ProductDTO> list=productService.getAllProductsOfProductMaster(productMasterId, pageNumber);
	    	if(list.hasContent())
	    	{
	    		QueryResponseDTO queryResponsedto=new QueryResponseDTO();
		    	queryResponsedto.setList(list.getContent());
		    	queryResponsedto.setCurrentPageNumber(list.getNumber());
		    	queryResponsedto.setTotalNoOfPages(list.getTotalPages());
		    	return new ResponseEntity<>(queryResponsedto,HttpStatus.OK);	
	    	}
	    	else
	    	{
				return new ResponseEntity<>("No Products available",HttpStatus.BAD_REQUEST);	
	    	}
	    }
	    else
	    {
	    	return new ResponseEntity<>("Either productMasterid invalid or pageNumber invalid",HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping(value = "/getproductsofuser/{userId}/{pageNumber}",produces = {"application/json"})
	public ResponseEntity<Object> getProductsOfUser(@PathVariable("userId") Long userId,@PathVariable("pageNumber") int pageNumber)
	{
		if(pageNumber>=0)
		{
		 Page<ProductDTO> list=productService.getAllProductsForUser(userId,pageNumber);
		 if(list.hasContent())
		 {
			 QueryResponseDTO queryResponsedto=new QueryResponseDTO();
		    	queryResponsedto.setList(list.getContent());
		    	queryResponsedto.setCurrentPageNumber(list.getNumber());
		    	queryResponsedto.setTotalNoOfPages(list.getTotalPages());
		    	return new ResponseEntity<>(queryResponsedto,HttpStatus.OK);
		 }
		 else
		 {
			return new ResponseEntity<>("No Products available",HttpStatus.BAD_REQUEST);
		 }
		}
		else
		{
			return new ResponseEntity<>("Invalid product page requested",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value="/searchproduct/{search}/{pageNumber}")
	public ResponseEntity<Object> searchProduct(@PathVariable("search") String search,@PathVariable("pageNumber") int pageNumber)
	{
		if(!search.trim().isEmpty())
		{
			Page<ProductDTO> list=productService.searchProduct(search,pageNumber);
		    if(list.hasContent())
		    {
		    	QueryResponseDTO queryResponsedto=new QueryResponseDTO();
		    	queryResponsedto.setList(list.getContent());
		    	queryResponsedto.setCurrentPageNumber(list.getNumber());
		    	queryResponsedto.setTotalNoOfPages(list.getTotalPages());
		    	return new ResponseEntity<>(queryResponsedto,HttpStatus.OK);
		    }
		    else
		    	return new ResponseEntity<>("No product found",HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<>("Please Provide valid Search keyword",HttpStatus.BAD_REQUEST);
		}
	}
	
}
