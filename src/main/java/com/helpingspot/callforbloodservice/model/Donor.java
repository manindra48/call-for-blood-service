package com.helpingspot.callforbloodservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min =  2, message="Name should have atleast 2 characters")
	private String name;
	
	@Past
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@Size(min =  10, message="Phone Number should have atleast 10 numbers")
	private String phoneNumber;
	
	private String bloodGroup;
	private String country;
	private String state;
	private String district;
	private String city;
	private Boolean availability;
	
	public Donor() {
		
	}
	
	public Donor(String name, Integer id, Date birthDate, String phoneNumber, String bloodGroup, String country,
			String state, String district, String city, Boolean availability) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.bloodGroup = bloodGroup;
		this.country = country;
		this.state = state;
		this.district = district;
		this.city = city;
		this.availability = availability;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	@Override
	public String toString() {
		return "Donor [name=" + name + ", id=" + id + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber
				+ ", bloodGroup=" + bloodGroup + ", country=" + country + ", state=" + state + ", district=" + district
				+ ", city=" + city + ", availability=" + availability + "]";
	}

}
