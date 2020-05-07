package com.cpg.go.exceptions;

import java.util.Map;

public class LoginException extends RuntimeException{

	private Map<String,String> errorsMap;
	
	public LoginException(Map<String,String> errorsMap)
	{
		this.errorsMap=errorsMap;
	}

	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}

	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}
	
	
}
