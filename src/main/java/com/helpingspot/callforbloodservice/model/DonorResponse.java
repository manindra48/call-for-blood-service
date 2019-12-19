package com.helpingspot.callforbloodservice.model;

public class DonorResponse {

	private String name;
	private String phoneNumber;
	private String bloodGroup;
	
	public DonorResponse() {
		
	}
	
	public DonorResponse(String name, String phoneNumber, String bloodGroup) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.bloodGroup = bloodGroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
