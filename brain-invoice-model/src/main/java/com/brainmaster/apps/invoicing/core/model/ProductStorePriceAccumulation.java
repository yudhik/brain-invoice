package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "product_store_price_accumulation")
@NamedQueries({@NamedQuery(
    name = "latest-product-store-average-price",
    query = "from ProductStorePriceAccumulation a where a.productStore = :productStore "
        + "and a.transactionDate = (select MAX(b.transactionDate) from ProductStorePriceAccumulation b where b.productStore = :productStore)")})
public class ProductStorePriceAccumulation extends AbstractCreateByEntity implements Serializable {

  private static final long serialVersionUID = 284359319157578920L;

  @Id
  @Type(type = "uuid")
  @Column(name = "price_accumulation_id", length = DatabaseColumnConstant.SIZE_UUID)
  private UUID priceAccumulationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_store_id")
  private ProductStore productStore;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "document_id", nullable = true)
  private RetailDocument retailDocument;

  @Column(name = "average_price")
  private BigDecimal averagePrice;

  @Column(name = "updated_price")
  private BigDecimal updatedPrice;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "transaction_date")
  private Date transactionDate;

  @Deprecated
  public ProductStorePriceAccumulation() {
    super(null);
  }

  public ProductStorePriceAccumulation(UUID priceAccumulationId, ProductStore productStore,
      RetailDocument retailDocument, BigDecimal averagePrice, BigDecimal updatedPrice,
      Date transactionDate, User createdBy) {
    super(createdBy);
    this.priceAccumulationId = priceAccumulationId;
    this.productStore = productStore;
    this.retailDocument = retailDocument;
    this.averagePrice = averagePrice;
    this.updatedPrice = updatedPrice;
    this.transactionDate = transactionDate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductStorePriceAccumulation other = (ProductStorePriceAccumulation) obj;
    if (averagePrice == null) {
      if (other.averagePrice != null)
        return false;
    } else if (!averagePrice.equals(other.averagePrice))
      return false;
    if (productStore == null) {
      if (other.productStore != null)
        return false;
    } else if (!productStore.equals(other.productStore))
      return false;
    if (retailDocument == null) {
      if (other.retailDocument != null)
        return false;
    } else if (!retailDocument.equals(other.retailDocument))
      return false;
    if (transactionDate == null) {
      if (other.transactionDate != null)
        return false;
    } else if (!transactionDate.equals(other.transactionDate))
      return false;
    if (updatedPrice == null) {
      if (other.updatedPrice != null)
        return false;
    } else if (!updatedPrice.equals(other.updatedPrice))
      return false;
    return true;
  }

  public BigDecimal getAveragePrice() {
    return averagePrice;
  }

  public UUID getPriceAccumulationId() {
    return priceAccumulationId;
  }

  public ProductStore getProductStore() {
    return productStore;
  }

  public RetailDocument getRetailDocument() {
    return retailDocument;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public BigDecimal getUpdatedPrice() {
    return updatedPrice;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((averagePrice == null) ? 0 : averagePrice.hashCode());
    result = prime * result + ((productStore == null) ? 0 : productStore.hashCode());
    result = prime * result + ((retailDocument == null) ? 0 : retailDocument.hashCode());
    result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
    result = prime * result + ((updatedPrice == null) ? 0 : updatedPrice.hashCode());
    return result;
  }

  public void setAveragePrice(BigDecimal averagePrice) {
    this.averagePrice = averagePrice;
  }

  public void setPriceAccumulationId(UUID priceAccumulationId) {
    this.priceAccumulationId = priceAccumulationId;
  }

  public void setProductStore(ProductStore productStore) {
    this.productStore = productStore;
  }

  public void setRetailDocument(RetailDocument retailDocument) {
    this.retailDocument = retailDocument;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public void setUpdatedPrice(BigDecimal updatedPrice) {
    this.updatedPrice = updatedPrice;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductStorePriceAccumulation [priceAccumulationId=");
    builder.append(priceAccumulationId);
    builder.append(", productStore=");
    builder.append(productStore);
    builder.append(", retailDocument=");
    builder.append(retailDocument);
    builder.append(", averagePrice=");
    builder.append(averagePrice);
    builder.append(", updatedPrice=");
    builder.append(updatedPrice);
    builder.append(", transactionDate=");
    builder.append(transactionDate);
    builder.append("]");
    return builder.toString();
  }

}
