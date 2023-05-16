package com.example.Signupdto;

public class LoginDTO {
	private Long Id;
	private String UserName;
	private String Password;
	public LoginDTO() {}
	public LoginDTO(String UserName,String Password) {
		this.UserName=UserName;
		this.Password=Password;
	}
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
}
