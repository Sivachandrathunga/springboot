package com.mphasis.parent.model.dto;
 
import java.util.List;
 
import lombok.Data;
 
 
 
@Data
public class FileLoadResponseDto {
	private String filename;
	private int recordCount;
	private String status;
	private String loadtime;
	private List<String> errors;
	
	public FileLoadResponseDto(String filename, int recordCount, String status, String loadtime, List<String> errors) {
		super();
		this.filename = filename;
		this.recordCount = recordCount;
		this.status = status;
		this.loadtime = loadtime;
		this.errors = errors;
	}
	
	public FileLoadResponseDto() {
		super();
	}
 
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoadtime() {
		return loadtime;
	}
	public void setLoadtime(String loadtime) {
		this.loadtime = loadtime;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
 