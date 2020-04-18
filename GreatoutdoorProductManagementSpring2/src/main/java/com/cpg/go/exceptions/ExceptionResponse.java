package com.cpg.go.exceptions;

import java.util.Date;
import java.util.Map;

public class ExceptionResponse {

	private Date timeStamp;
	private String status;
	private Map<String,Map<String,String>> listOfErrors;
	
	public ExceptionResponse(Date timeStamp,String status,Map<String,Map<String,String>> listOfErrors)
	{
		this.timeStamp=timeStamp;
		this.status=status;
		this.listOfErrors=listOfErrors;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Map<String, String>> getListOfErrors() {
		return listOfErrors;
	}

	public void setListOfErrors(Map<String, Map<String, String>> listOfErrors) {
		this.listOfErrors = listOfErrors;
	}
	
	
}
