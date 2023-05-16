package com.example.Signupdto;

import java.util.List;
import java.util.Set;

import com.example.model.RoleEntity;
import com.example.model.UserDetails;

public class SignupDTO {
	private Long Id;
	private String UserName;
	private String Password;
	private String roles;
	private List<UserDetails> userDetails;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public List<UserDetails> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	
}
