package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.model.id.ProductAccountKeys;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2520437947468554143L;

	@EmbeddedId
	private ProductAccountKeys keys;

	@Type(type = "uuid")
	@Column(name = "system_uuid", unique = true)
	private UUID systemUuid;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "barcode_number")
	private String barcodeNumber;

	@ManyToOne(targetEntity = PackagingUnit.class)
	@JoinColumns({
		@JoinColumn(name = "packaging_id"),
		@JoinColumn(name = "packaging_account_id")
	})
	private PackagingUnit packageCode;

	@ManyToOne(targetEntity = Brand.class)
	@JoinColumns({
		@JoinColumn(name = "brand_name"),
		@JoinColumn(name = "brand_account_id")
	})
	private Brand brand;

	@ManyToOne(targetEntity = Category.class)
	@JoinColumns({
		@JoinColumn(name = "category_name"),
		@JoinColumn(name = "category_account_id")
	})
	private Category category;

	// @OneToMany(mappedBy = "id.product", fetch = FetchType.LAZY, cascade =
	// CascadeType.ALL)
	// private List<ProductStore> stores = new ArrayList<ProductStore>();

	public Product() {
	}

	public Product(Account account, String productCode, String productName,
			String barcodeNumber, Brand brand, Category category,
			PackagingUnit packageCode) {
		this.keys = new ProductAccountKeys(account, productCode);
		this.systemUuid = UUID.randomUUID();
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

	public void setPackageCode(PackagingUnit packageCode) {
		this.packageCode = packageCode;
	}

	public PackagingUnit getPackageCode() {
		return packageCode;
	}

	public UUID getSystemUuid() {
		return systemUuid;
	}

	public void setSystemUuid(UUID systemUuid) {
		this.systemUuid = systemUuid;
	}

	@Transient
	public String getSystemId() {
		return UUIDHelper.uuidToString(systemUuid);
	}

	// public void setStores(List<ProductStore> stores) {
	// this.stores = stores;
	// }
	//
	// public List<ProductStore> getStores() {
	// return stores;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((barcodeNumber == null) ? 0 : barcodeNumber.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result
				+ ((packageCode == null) ? 0 : packageCode.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result
				+ ((systemUuid == null) ? 0 : systemUuid.hashCode());
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
		if (barcodeNumber == null) {
			if (other.barcodeNumber != null)
				return false;
		} else if (!barcodeNumber.equals(other.barcodeNumber))
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
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		if (packageCode == null) {
			if (other.packageCode != null)
				return false;
		} else if (!packageCode.equals(other.packageCode))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (systemUuid == null) {
			if (other.systemUuid != null)
				return false;
		} else if (!systemUuid.equals(other.systemUuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [keys=" + keys + ", systemUuid=" + systemUuid
				+ ", productName=" + productName + ", barcodeNumber="
				+ barcodeNumber + ", packageCode=" + packageCode + ", brand="
				+ brand + ", category=" + category + "]";
	}
}
