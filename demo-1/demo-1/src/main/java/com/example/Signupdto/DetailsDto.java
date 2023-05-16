package com.example.Signupdto;

import java.util.Date;

import com.example.model.UserDetails;


public class DetailsDto {
	private String firstName;
    private String lastName;
	private String phoneNo;
	private String Email;
	private String dob;
	private String Gender;
	private String Address;
	private String Country;
	private String State;
	private Long id;
	private String photos;
	public DetailsDto() {
		// TODO Auto-generated constructor stub
	}
	public DetailsDto(String firstName, String lastName, String Email, String phoneNo, String dob, String Address, String Country, String Gender, String State, Long id, String photos) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.Email=Email;
		this.phoneNo=phoneNo;
		this.dob=dob;
		this.Address=Address;
		this.Country=Country;
		this.Gender=Gender;
		this.State=State;
	    this.id=id;
	    this.photos=photos;
	}
	public DetailsDto(UserDetails userDetails) {
		this.firstName=userDetails.getFirstName();
		this.lastName=userDetails.getLastName();
		this.phoneNo=userDetails.getPhoneNo();
		this.Email=userDetails.getEmail();
		this.dob=userDetails.getDob();
		this.Address=userDetails.getAddress();
		this.Country=userDetails.getCountry();
		this.Gender=userDetails.getGender();
		this.State=userDetails.getState();
		this.id=userDetails.getId();
		this.photos=userDetails.getPhotos();
		// TODO Auto-generated constructor stub
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	

}
