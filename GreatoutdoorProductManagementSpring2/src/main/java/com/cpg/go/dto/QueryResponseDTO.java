package com.cpg.go.dto;

import java.util.List;


public class QueryResponseDTO {

	 private List<ProductDTO> list;
	 private long totalNoOfPages;
	 private long currentPageNumber;
	public List<ProductDTO> getList() {
		return list;
	}
	public void setList(List<ProductDTO> list) {
		this.list = list;
	}
	public long getTotalNoOfPages() {
		return totalNoOfPages;
	}
	public void setTotalNoOfPages(long totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}
	public long getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(long currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	 
	
	 
}
