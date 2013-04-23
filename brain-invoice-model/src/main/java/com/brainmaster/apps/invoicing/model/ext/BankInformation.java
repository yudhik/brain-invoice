package com.brainmaster.apps.invoicing.model.ext;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BankInformation implements Serializable {

	private static final long serialVersionUID = -123335430119266972L;

	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_branch")
	private String bankBranch;
	
	@Column(name = "bank_code")
	private String bankCode;
	
	@Column(name = "bank_address1")
	private String bankAddress1;

	@Column(name = "bank_address2")
	private String bankAddress2;

	@Column(name = "bank_address3")
	private String bankAddress3;

	@Column(name = "bank_postal_code")
	private String bankPostalCode;

	@Column(name = "bank_city")
	private String bankCity;

	@Column(name = "bank_province")
	private String bankProvince;
	
	public BankInformation() {
	}

	public BankInformation(String accountName, String accountNumber,
			String bankName, String bankBranch, String bankCode,
			String bankAddress1, String bankAddress2, String bankAddress3,
			String bankPostalCode, String bankCity, String bankProvince) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.bankCode = bankCode;
		this.bankAddress1 = bankAddress1;
		this.bankAddress2 = bankAddress2;
		this.bankAddress3 = bankAddress3;
		this.bankPostalCode = bankPostalCode;
		this.bankCity = bankCity;
		this.bankProvince = bankProvince;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAddress1() {
		return bankAddress1;
	}

	public void setBankAddress1(String bankAddress1) {
		this.bankAddress1 = bankAddress1;
	}

	public String getBankAddress2() {
		return bankAddress2;
	}

	public void setBankAddress2(String bankAddress2) {
		this.bankAddress2 = bankAddress2;
	}

	public String getBankAddress3() {
		return bankAddress3;
	}

	public void setBankAddress3(String bankAddress3) {
		this.bankAddress3 = bankAddress3;
	}

	public String getBankPostalCode() {
		return bankPostalCode;
	}

	public void setBankPostalCode(String bankPostalCode) {
		this.bankPostalCode = bankPostalCode;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	
	
	
}
