package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.model.id.ProductAccountKeys;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2520437947468554143L;

	@EmbeddedId
	private ProductAccountKeys keys;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "barcode_number")
	private String barcodeNumber;

	@JoinColumn(name = "package_code")
	@ManyToOne(targetEntity = PackagingValue.class)
	private PackagingValue packageCode;

	@ManyToOne(targetEntity = Brand.class)
	private Brand brand;

	@ManyToOne(targetEntity = Category.class)
	private Category category;

	@OneToMany(mappedBy = "id.product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductStore> stores = new ArrayList<ProductStore>();

	public Product() {
	}

	public Product(Account account, String productCode, String productName,
			String barcodeNumber, Brand brand, Category category,
			PackagingValue packageCode) {
		this.keys = new ProductAccountKeys(account, productCode);
		this.productName = productName;
		this.barcodeNumber = barcodeNumber;
		this.brand = brand;
		this.category = category;
		this.packageCode = packageCode;
	}
	
	public ProductAccountKeys getKeys() {
		return keys;
	}

	public void setKeys(ProductAccountKeys keys) {
		this.keys = keys;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBarcodeNumber() {
		return barcodeNumber;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPackageCode(PackagingValue packageCode) {
		this.packageCode = packageCode;
	}

	public PackagingValue getPackageCode() {
		return packageCode;
	}

	public void setStores(List<ProductStore> stores) {
		this.stores = stores;
	}

	public List<ProductStore> getStores() {
		return stores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		return result;
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
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

}
