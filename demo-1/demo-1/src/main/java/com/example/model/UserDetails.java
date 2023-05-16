package com.example.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetails {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Column(name="firstname")
private String firstName;
@Column(name="lastname")
private String lastName;
@Column(name="phoneno")
private String phoneNo;
@Column(name="email")
private String Email;
@Column(name="date_of_birth")
private String dob;
@Column(name="gender")
private String Gender;
@Column(name="address")
private String Address;
@Column(name="country")
private String Country;
@Column(name="state")
private String State;
private String photos;
public UserDetails() {}
public UserDetails(Long id,String firstName,String lastName,String phoneNo,String email, String dob, String Gender, String Address, String Country, String State)
{
	super();
	this.id=id;
	this.firstName=firstName;
	this.lastName=lastName;
	this.Email=email;
	this.phoneNo=phoneNo;
	this.dob=dob;
	this.Gender=Gender;
	this.Address=Address;
	this.Country=Country;
	this.State=State;
	
}
public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}


public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getGender() {
	return Gender;
}
public void setGender(String gender) {
	Gender = gender;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCountry() {
	return Country;
}
public void setCountry(String country) {
	Country = country;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public String getPhotos() {
	return photos;
}
public void setPhotos(String photos) {
	this.photos = photos;
}




}
