package com.helpingspot.callforbloodservice.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "is required")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String lastName;

	// @Past
	// @JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@NotNull(message = "is required")
	@Size(min = 10, message = "Phone Number should have atleast 10 numbers")
	private String phoneNumber;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;

//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
//	private String matchingPassword;

	@Email
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String bloodGroup;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String country;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String state;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String district;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String city;

//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
	private Boolean availability;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	public Donor() {

	}

	public Donor(Integer id,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String userName,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String firstName,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String lastName,
			@Past Date birthDate,
			@NotNull(message = "is required") @Size(min = 10, message = "Phone Number should have atleast 10 numbers") String phoneNumber,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password,
			// @NotNull(message = "is required") @Size(min = 1, message = "is required")
			// String matchingPassword,
			@Email @NotNull(message = "is required") @Size(min = 1, message = "is required") String email,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String bloodGroup,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String country,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String state,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String district,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String city,
			// @NotNull(message = "is required") @Size(min = 1, message = "is required")
			// Boolean availability
			@NotNull Boolean availability) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
		// this.matchingPassword = matchingPassword;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.country = country;
		this.state = state;
		this.district = district;
		this.city = city;
		this.availability = availability;
	}

	public Donor(Integer id,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String userName,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String firstName,
			@NotNull(message = "is required") @Size(min = 2, message = "Name should have atleast 2 characters") String lastName,
			@Past Date birthDate,
			@NotNull(message = "is required") @Size(min = 10, message = "Phone Number should have atleast 10 numbers") String phoneNumber,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password,
			@Email @NotNull(message = "is required") @Size(min = 1, message = "is required") String email,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String bloodGroup,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String country,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String state,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String district,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String city,
			// @NotNull(message = "is required") @Size(min = 1, message = "is required")
			// Boolean availability,
			@NotNull Boolean availability, Collection<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.country = country;
		this.state = state;
		this.district = district;
		this.city = city;
		this.availability = availability;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getMatchingPassword() {
//		return matchingPassword;
//	}
//
//	public void setMatchingPassword(String matchingPassword) {
//		this.matchingPassword = matchingPassword;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Donor [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + ", password=" + password + ", email="
				+ email + ", bloodGroup=" + bloodGroup + ", country=" + country + ", state=" + state + ", district="
				+ district + ", city=" + city + ", availability=" + availability + ", roles=" + roles + "]";
	}

//	@Override
//	public String toString() {
//		return "Donor [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + ", password=" + password + ", email="
//				+ email + ", bloodGroup=" + bloodGroup
//				// + ", matchingPassword=" + matchingPassword + ", email=" + email + ",
//				// bloodGroup=" + bloodGroup
//				+ ", country=" + country + ", state=" + state + ", district=" + district + ", city=" + city
//				+ ", availability=" + availability + "]";
//	}

}
