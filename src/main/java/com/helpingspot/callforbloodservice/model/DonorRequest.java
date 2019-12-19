package com.helpingspot.callforbloodservice.model;

public class DonorRequest {
	
	private String bloodGroup;
	private String country;
	private String state;
	private String district;
	private String city;
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
	@Override
	public String toString() {
		return "DonorRequest [bloodGroup=" + bloodGroup + ", country=" + country + ", state=" + state + ", district="
				+ district + ", city=" + city + "]";
	}
	

}
