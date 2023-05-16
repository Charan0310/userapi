package com.example.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity

@Table(name="userdb",schema="public")
public class UserEntity {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long Id;
@Column(name="username")
private String userName;
@Column(name="password")
private String password;

public UserEntity() {}
public UserEntity(String UserName,String Password) {
	super();
	this.userName=UserName;
	this.password=Password;
}
public Long getId() {
	return Id;
}
public void setId(Long Id) {
	this.Id=Id;
}
public String getuserName() {
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

@ManyToOne()
@JoinTable(name = "user_roles",
joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",unique = false),
inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id",unique=false))
@JoinColumn(name="role_id")

private RoleEntity roles;
public RoleEntity getRoles() {
return roles;

}
public void setRoles(RoleEntity roles) {
this.roles=roles;
}
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "user_id",referencedColumnName = "id")
private List<UserDetails> userDetails;
public List<UserDetails> getUserDetails(){
	return userDetails;
}
public void setUserDetails(List<UserDetails> userDetails) {
	this.userDetails=userDetails;
}


}
