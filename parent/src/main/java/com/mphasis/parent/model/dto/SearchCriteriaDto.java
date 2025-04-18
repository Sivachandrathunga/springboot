package com.mphasis.parent.model.dto;
 
public class SearchCriteriaDto {
	
    private String filename;
    private String status;
    private String startDate;
    private String endDate;
    
    public SearchCriteriaDto() {
		super();
	}
 
 
	public SearchCriteriaDto(String filename, String status, String startDate, String endDate) {
		super();
		this.filename = filename;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}
 
    
    public String getFilename() {
        return filename;
    }
 
    public void setFilename(String filename) {
        this.filename = filename;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public String getStartDate() {
        return startDate;
    }
 
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
 
    public String getEndDate() {
        return endDate;
    }
 
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
 
 