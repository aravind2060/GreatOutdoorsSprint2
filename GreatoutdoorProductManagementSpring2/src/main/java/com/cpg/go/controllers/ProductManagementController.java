package com.cpg.go.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.go.dto.ProductDTO;
import com.cpg.go.exceptions.ProductException;
import com.cpg.go.service.ProductServiceImpl;

@RestController
@CrossOrigin
public class ProductManagementController {

	

	@Autowired
	ProductServiceImpl productService;
	//TODO only valid product master can add
	@PostMapping(value = "/addproduct",consumes = {"application/json","application/xml"})
	public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDTO productDTO,BindingResult bindingResult)
	{		System.out.println(productDTO);
	     
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
			 return new ResponseEntity<>("Product Added SuccessFully",HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<>("Unable to add product",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(value="/getproduct/{id}",produces = {"application/json","application/xml"})
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
	@GetMapping(value="/deleteproduct")
	public ResponseEntity<Object> deleteProductById(@RequestParam("id") long id)
	{
		if(id>0)
		{
			if(productService.deleteProductById(id))
				return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
			else
				return new ResponseEntity<>("Either specified id doesn't exist or problem encountered while deleting",HttpStatus.BAD_REQUEST);
		}else
		{
			return new ResponseEntity<>("Product Id InValid",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//TODO only valid product master can update
	@PutMapping(value="/editproduct",consumes= {"application/json","application/xml"})
	public ResponseEntity<Object> editProduct(@RequestBody ProductDTO productDTO)
	{
		if(productDTO!=null)
		{
			if(productService.editProduct(productDTO))
				return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
			else
				return new ResponseEntity<>("Either Specified product doesnot belongs to you or some problem encountered  while updating",HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<>("Provide Valid Entity",HttpStatus.BAD_REQUEST);
		}
	}
	
}
