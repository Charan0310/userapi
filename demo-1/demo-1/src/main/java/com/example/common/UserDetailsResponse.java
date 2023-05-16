package com.example.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;

public class UserDetailsResponse {
private Object firstname;
private Object lastname;
private Object phoneno;
private Object email;
private Object id;


private String dob;
private String Gender;
private String Address;
private String Country;
private String State;
private String photos;
public UserDetailsResponse() {}
public UserDetailsResponse( Object firstname,Object lastname,Object phoneno,Object email, Object roles, String dob, String Gender, String Address, String Country, String State, String photos) {super();
this.firstname=firstname;
this.lastname=lastname;
this.phoneno=phoneno;
this.email=email;
this.photos=photos;
this.dob=dob;
this.Gender=Gender;
this.Address=Address;
this.Country=Country;
this.State=State;
}

public Object getFirstname() {
	return firstname;
}
public void setFirstname(Object firstname) {
	this.firstname = firstname;
}
public Object getLastname() {
	return lastname;
}
public void setLastname(Object lastname) {
	this.lastname = lastname;
}
public Object getPhoneno() {
	return phoneno;
}
public void setPhoneno(Object phoneno) {
	this.phoneno = phoneno;
}
public Object getEmail() {
	return email;
}
public void setEmail(Object email) {
	this.email = email;
}
public Object getId() {
	return id;
}
public void setId(Object id) {
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
