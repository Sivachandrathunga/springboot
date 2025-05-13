package com.mphasis.parent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
 
@Entity
@Data
public class UserDetails {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for MySQL
    private long id;
    private String userName;
    private String password;
    private String token;
    private String Authorities;
 
    public UserDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
 
    public UserDetails() {
        super();
    }
 
	public long getId() {
		return id;
	}
 
	public void setId(long id) {
		this.id = id;
	}
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getToken() {
		return token;
	}
 
	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthorities() {
		return Authorities;
	}

	public void setAuthorities(String authorities) {
		Authorities = authorities;
	}
    
}