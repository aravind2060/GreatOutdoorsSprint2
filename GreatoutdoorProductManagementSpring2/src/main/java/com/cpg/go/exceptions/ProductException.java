package com.cpg.go.exceptions;

import java.util.Map;

public class ProductException extends RuntimeException{
  
	private Map<String,Map<String,String>> errorsmap;
	
	public ProductException(Map<String,Map<String,String>> map)
	{
		this.errorsmap=map;
	}
	
	public Map<String,Map<String,String>> getErrorsMap()
	{
		return errorsmap;
	}
}
