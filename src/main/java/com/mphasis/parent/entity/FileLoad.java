package com.mphasis.parent.entity;
 
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
 
@Entity
@Data
public class FileLoad {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private String Filename;
	private String status;
	private int recordCount;
	private List<String> errors;
	@CreationTimestamp
	private LocalDate localdate;
	public FileLoad(long id, String filename, String status, int recordCount, List<String> errors,
			LocalDate localdate) {
		super();
		this.id = id;
		this.Filename = filename;
		this.status = status;
		this.recordCount = recordCount;
		this.errors = errors;
		this.localdate = localdate;
	}
	public FileLoad() {
	}
	private String criteria;
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
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
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public LocalDate getLocaldate() {
		return localdate;
	}
	public void setLocaldate(LocalDate localdate) {
		this.localdate = localdate;
	}
	public static List<FileLoad> getAllFiles() {
		return null;
	}
	public static FileLoad getFileLoadById(Long id2) {
		
		return null;
	}
	
}