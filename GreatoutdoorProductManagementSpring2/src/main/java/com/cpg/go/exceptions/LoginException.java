package com.cpg.go.exceptions;

import java.util.Map;

public class LoginException extends RuntimeException{

	Map<String,String> errorsMap;
	
	public LoginException(Map<String,String> errorsMap)
	{
		this.errorsMap=errorsMap;
	}
}
