package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.ProductAccountKeys;

@Entity
@IdClass(ProductAccountKeys.class)
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"product_code",
    "account_id"}))
@NamedQueries({@NamedQuery(name = "product-with-code",
    query = "select a from Product a where a.productCode = :productCode and a.account = :account")})
public class Product extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = -2520437947468554143L;

  @Id
  @Column(name = "product_code")
  private String productCode;

  @Id
  @ManyToOne(targetEntity = Account.class)
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "barcode_number")
  private String barcodeNumber;

  @ManyToOne(targetEntity = PackagingUnit.class, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumns({
      @PrimaryKeyJoinColumn(name = "packaging_id", referencedColumnName = "packaging_id"),
      @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id")})
  private PackagingUnit packageCode;

  @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumns({
      @PrimaryKeyJoinColumn(name = "brand_name", referencedColumnName = "brand_name"),
      @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id")})
  private Brand brand;

  @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumns({
      @PrimaryKeyJoinColumn(name = "category_name", referencedColumnName = "category_name"),
      @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id")})
  private Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProductStore> stores = new ArrayList<ProductStore>();

  @Deprecated
  public Product() {
    super(null, null);
  }

  public Product(Account account, String productCode, String productName, String barcodeNumber,
      Brand brand, Category category, PackagingUnit packageCode, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.account = account;
    this.productCode = productCode;
    this.productName = productName;
    this.barcodeNumber = barcodeNumber;
    this.brand = brand;
    this.category = category;
    this.packageCode = packageCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (account == null) {
      if (other.account != null)
        return false;
    } else if (!account.equals(other.account))
      return false;
    if (brand == null) {
      if (other.brand != null)
        return false;
    } else if (!brand.equals(other.brand))
      return false;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (packageCode == null) {
      if (other.packageCode != null)
        return false;
    } else if (!packageCode.equals(other.packageCode))
      return false;
    if (productCode == null) {
      if (other.productCode != null)
        return false;
    } else if (!productCode.equals(other.productCode))
      return false;
    return true;
  }

  public Account getAccount() {
    return account;
  }

  public String getBarcodeNumber() {
    return barcodeNumber;
  }

  public Brand getBrand() {
    return brand;
  }

  public Category getCategory() {
    return category;
  }

  public PackagingUnit getPackageCode() {
    return packageCode;
  }

  public String getProductCode() {
    return productCode;
  }

  public String getProductName() {
    return productName;
  }

  public List<ProductStore> getStores() {
    return stores;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    result = prime * result + ((brand == null) ? 0 : brand.hashCode());
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((packageCode == null) ? 0 : packageCode.hashCode());
    result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setBarcodeNumber(String barcodeNumber) {
    this.barcodeNumber = barcodeNumber;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setPackageCode(PackagingUnit packageCode) {
    this.packageCode = packageCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setStores(List<ProductStore> stores) {
    this.stores = stores;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Product [");
    builder.append("productCode=");
    builder.append(productCode);
    builder.append(", account=");
    builder.append(account);
    builder.append(", productName=");
    builder.append(productName);
    builder.append(", barcodeNumber=");
    builder.append(barcodeNumber);
    builder.append(", packageCode=");
    builder.append(packageCode);
    builder.append(", brand=");
    builder.append(brand);
    builder.append(", category=");
    builder.append(category);
    builder.append("]");
    return builder.toString();
  }

}
