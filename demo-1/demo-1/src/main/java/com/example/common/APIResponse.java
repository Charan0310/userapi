package com.example.common;

import java.util.Set;

import com.example.model.RoleEntity;

import io.swagger.annotations.ApiResponse;

public class APIResponse {
private Boolean status;
private Object token;
private String message;
 public APIResponse() {
	// TODO Auto-generated constructor stub
}
public APIResponse(Boolean status,Object token) {
	super();
	this.status=status;
	this.token=token;
}
public APIResponse(Boolean status,String message) {
	super();
	this.status=status;
	this.message=message;
}
public Object getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}
public Object getToken() {
	return token;
}
public void setToken(Object token) {
	this.token = token;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
