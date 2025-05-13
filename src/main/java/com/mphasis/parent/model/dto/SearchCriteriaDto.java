package com.mphasis.parent.model.dto;
 
public class SearchCriteriaDto {
	
    private String filename;
    private String status;
    ;
    
    public SearchCriteriaDto() {
		super();
	}
 
 
	public SearchCriteriaDto(String filename, String status) {
		super();
		this.filename = filename;
		this.status = status;
		
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
 
}
 
 