package com.mphasis.parent.model.dto;
 
import lombok.Data;
 
@Data
public class FileLoadRequestDto {
	
	public FileLoadRequestDto() {
		super();
	}
	public FileLoadRequestDto(String filename, String status, int recordCount) {
		super();
		this.filename = filename;
		this.status = status;
		this.recordCount = recordCount;
	}
	private String filename;
	private String status;
	private int recordCount;
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
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
}