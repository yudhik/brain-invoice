package com.brainmaster.apps.invoicing.core.model.ext;

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

  public BankInformation() {}

  public BankInformation(String accountName, String accountNumber, String bankName,
      String bankBranch, String bankCode, String bankAddress1, String bankAddress2,
      String bankAddress3, String bankPostalCode, String bankCity, String bankProvince) {
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BankInformation other = (BankInformation) obj;
    if (accountName == null) {
      if (other.accountName != null)
        return false;
    } else if (!accountName.equals(other.accountName))
      return false;
    if (accountNumber == null) {
      if (other.accountNumber != null)
        return false;
    } else if (!accountNumber.equals(other.accountNumber))
      return false;
    if (bankAddress1 == null) {
      if (other.bankAddress1 != null)
        return false;
    } else if (!bankAddress1.equals(other.bankAddress1))
      return false;
    if (bankAddress2 == null) {
      if (other.bankAddress2 != null)
        return false;
    } else if (!bankAddress2.equals(other.bankAddress2))
      return false;
    if (bankAddress3 == null) {
      if (other.bankAddress3 != null)
        return false;
    } else if (!bankAddress3.equals(other.bankAddress3))
      return false;
    if (bankBranch == null) {
      if (other.bankBranch != null)
        return false;
    } else if (!bankBranch.equals(other.bankBranch))
      return false;
    if (bankCity == null) {
      if (other.bankCity != null)
        return false;
    } else if (!bankCity.equals(other.bankCity))
      return false;
    if (bankCode == null) {
      if (other.bankCode != null)
        return false;
    } else if (!bankCode.equals(other.bankCode))
      return false;
    if (bankName == null) {
      if (other.bankName != null)
        return false;
    } else if (!bankName.equals(other.bankName))
      return false;
    if (bankPostalCode == null) {
      if (other.bankPostalCode != null)
        return false;
    } else if (!bankPostalCode.equals(other.bankPostalCode))
      return false;
    if (bankProvince == null) {
      if (other.bankProvince != null)
        return false;
    } else if (!bankProvince.equals(other.bankProvince))
      return false;
    return true;
  }

  public String getAccountName() {
    return accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getBankAddress1() {
    return bankAddress1;
  }

  public String getBankAddress2() {
    return bankAddress2;
  }

  public String getBankAddress3() {
    return bankAddress3;
  }

  public String getBankBranch() {
    return bankBranch;
  }

  public String getBankCity() {
    return bankCity;
  }

  public String getBankCode() {
    return bankCode;
  }

  public String getBankName() {
    return bankName;
  }

  public String getBankPostalCode() {
    return bankPostalCode;
  }

  public String getBankProvince() {
    return bankProvince;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
    result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
    result = prime * result + ((bankAddress1 == null) ? 0 : bankAddress1.hashCode());
    result = prime * result + ((bankAddress2 == null) ? 0 : bankAddress2.hashCode());
    result = prime * result + ((bankAddress3 == null) ? 0 : bankAddress3.hashCode());
    result = prime * result + ((bankBranch == null) ? 0 : bankBranch.hashCode());
    result = prime * result + ((bankCity == null) ? 0 : bankCity.hashCode());
    result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
    result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
    result = prime * result + ((bankPostalCode == null) ? 0 : bankPostalCode.hashCode());
    result = prime * result + ((bankProvince == null) ? 0 : bankProvince.hashCode());
    return result;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setBankAddress1(String bankAddress1) {
    this.bankAddress1 = bankAddress1;
  }

  public void setBankAddress2(String bankAddress2) {
    this.bankAddress2 = bankAddress2;
  }

  public void setBankAddress3(String bankAddress3) {
    this.bankAddress3 = bankAddress3;
  }

  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
  }

  public void setBankCity(String bankCity) {
    this.bankCity = bankCity;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public void setBankPostalCode(String bankPostalCode) {
    this.bankPostalCode = bankPostalCode;
  }

  public void setBankProvince(String bankProvince) {
    this.bankProvince = bankProvince;
  }

  @Override
  public String toString() {
    return "BankInformation [accountName=" + accountName + ", accountNumber=" + accountNumber
        + ", bankName=" + bankName + ", bankBranch=" + bankBranch + ", bankCode=" + bankCode
        + ", bankAddress1=" + bankAddress1 + ", bankAddress2=" + bankAddress2 + ", bankAddress3="
        + bankAddress3 + ", bankPostalCode=" + bankPostalCode + ", bankCity=" + bankCity
        + ", bankProvince=" + bankProvince + "]";
  }

}
