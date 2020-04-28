package com.cpg.go.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.go.dao.ProductDAO;
import com.cpg.go.dto.ProductDTO;
import com.cpg.go.exceptions.ProductException;
import com.cpg.go.service.ProductServiceImpl;

@RestController
@CrossOrigin
public class ProductManagementController {

	

	@Autowired
	ProductServiceImpl productService;
	
	//TODO add session
	@PostMapping(value = "/addproduct",consumes = {"application/json","application/xml"})
	public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDTO productDTO,BindingResult bindingResult)
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
		else if(productService.getProductById(productDTO.getProductId())!=null)
		{
			HashMap<String,Map<String,String>> map=new HashMap<>();
			HashMap<String,String> m=new HashMap<>();
			m.put("NotUnique", "Product with this id already exist");
			map.put("productId", m);
			throw new ProductException(map);
		}
		else
		{
			if(productService.addProduct(productDTO))
			{
			  List<String> list=new ArrayList<>();
			  list.add("Success");
			 return new ResponseEntity<>(list,HttpStatus.OK);
			}
			 else
				return new ResponseEntity<>("Unable to add product",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping(value="/getproductbyid/{id}",produces = {"application/json","application/xml"})
	public ResponseEntity<Object> getProductById(@PathVariable("id") long id)
	{
		if(id>0)
		{
		  ProductDTO product=productService.getProductById(id);
		  
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
	
	//TODO Only valid product master can delete
	@DeleteMapping(value="/deleteproduct/{id}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("id") long id)
	{
		if(id>0)
		{
			if(productService.getProductById(id)!=null)
			{
			  if(productService.deleteProductById(id))
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
	
	//TODO only valid product master can update
	@PutMapping(value="/updateproduct",consumes= {"application/json","application/xml"})
	public ResponseEntity<Object> editProduct(@RequestBody ProductDTO productDTO)
	{
		if(productDTO!=null)
		{
			if(productService.editProduct(productDTO))
				return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
			else
				return new ResponseEntity<>("Either Specified product doesnot belongs to you or some problem encountered  while updating",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity<>("Provide Valid Entity",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/viewproductsofproductmaster/{id}",produces= {"application/json"})
	public ResponseEntity<Object> getProductsOfProductMaster(@PathVariable("id") long id)
	{
	    List<ProductDTO> products=productService.getAllProductsOfProductMaster(id);
	    if(products.size()>0)
	    {
	      return new ResponseEntity<>(products,HttpStatus.OK);	
	    }
	    else
	    {
	    	return new ResponseEntity<>("No Products Found",HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping(value = "/getallproducts/{pageNumber}",produces = {"application/json"})
	public ResponseEntity<Object> getAllProducts(@PathVariable("pageNumber") int pageNumber)
	{
		if(pageNumber<=getTotalNoOfPagesExistForProducts())
		{
		 Page<ProductDTO> list=productService.getAllProductsForUser(pageNumber);
		 if(list.hasContent())
		 {
			return new ResponseEntity<>(list.getContent(),HttpStatus.OK);
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
	
	@GetMapping(value="/noofpagesexistforproducts")
	public long getTotalNoOfPagesExistForProducts()
	{
		return productService.getTotalNoOfPagesExistForProducts();
	}
}
