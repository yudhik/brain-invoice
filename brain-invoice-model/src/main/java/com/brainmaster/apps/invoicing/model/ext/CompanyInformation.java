package com.brainmaster.apps.invoicing.model.ext;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyInformation implements Serializable {

	private static final long serialVersionUID = -1241904448293531135L;

	@Column(name = "store_address1")
	private String address1;

	@Column(name = "store_address2")
	private String address2;

	@Column(name = "store_address3")
	private String address3;

	@Column(name = "store_postal_code")
	private String postalCode;

	@Column(name = "store_city")
	private String city;

	@Column(name = "store_province")
	private String province;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "fax_number")
	private String faxNumber;

	@Column(name = "mobile_number")
	private String mobileNumber;

	public CompanyInformation() {
	}

	public CompanyInformation(String address1, String address2,
			String address3, String postalCode, String city, String province,
			String emailAddress, String phoneNumber, String faxNumber,
			String mobileNumber) {
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.mobileNumber = mobileNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
