package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "product_store_transaction", uniqueConstraints = @UniqueConstraint(
    columnNames = {"transaction_hash"}))
@NamedQueries({@NamedQuery(
    name = "latest-product-store-quantity-transaction",
    query = "from ProductStoreTransaction a where a.productStore = :productStore "
        + "and a.transactionDate = (select MAX(b.transactionDate) from ProductStoreTransaction b where b.productStore = :productStore)")})
public class ProductStoreTransaction extends AbstractCreateByEntity implements Serializable {

  private static final long serialVersionUID = -5882851840070775615L;

  @Id
  @Type(type = "uuid")
  @Column(name = "transaction_id", length = DatabaseColumnConstant.SIZE_UUID)
  private UUID transactionId;

  @Column(name = "transaction_hash")
  private String transactionHashCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_store_id", nullable = false)
  private ProductStore productStore;

  @Column(name = "transaction_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactionDate;

  @Column(name = "outstanding_quantity", nullable = false)
  private Long outstandingQuantity;

  @Column(name = "quantity", nullable = false)
  private Long quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "document_id", nullable = false)
  private RetailDocument retailDocument;

  @Deprecated
  public ProductStoreTransaction() {
    super(null);
  }

  public ProductStoreTransaction(String transactionHashCode, ProductStore productStore,
      Date transactionDate, Long outstandingQuantity, Long quantity, RetailDocument retailDocument,
      User createdBy) {
    super(createdBy);
    this.transactionId = UUID.randomUUID();
    this.transactionHashCode = transactionHashCode;
    this.productStore = productStore;
    this.transactionDate = transactionDate;
    this.outstandingQuantity = outstandingQuantity;
    this.quantity = quantity;
    this.retailDocument = retailDocument;
  }

  public ProductStoreTransaction(UUID transactionId, String transactionHashCode,
      ProductStore productStore, Date transactionDate, Long outstandingQuantity, Long quantity,
      RetailDocument retailDocument, User createdBy) {
    super(createdBy);
    this.transactionId = transactionId;
    this.transactionHashCode = transactionHashCode;
    this.productStore = productStore;
    this.transactionDate = transactionDate;
    this.outstandingQuantity = outstandingQuantity;
    this.quantity = quantity;
    this.retailDocument = retailDocument;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductStoreTransaction other = (ProductStoreTransaction) obj;
    if (transactionHashCode == null) {
      if (other.transactionHashCode != null)
        return false;
    } else if (!transactionHashCode.equals(other.transactionHashCode))
      return false;
    return true;
  }

  public Long getOutstandingQuantity() {
    return outstandingQuantity;
  }

  public ProductStore getProductStore() {
    return productStore;
  }

  public Long getQuantity() {
    return quantity;
  }

  public RetailDocument getRetailDocument() {
    return retailDocument;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public String getTransactionHashCode() {
    return transactionHashCode;
  }

  public UUID getTransactionId() {
    return transactionId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((transactionHashCode == null) ? 0 : transactionHashCode.hashCode());
    return result;
  }

  public void setOutstandingQuantity(Long outstandingQuantity) {
    this.outstandingQuantity = outstandingQuantity;
  }

  public void setProductStore(ProductStore productStore) {
    this.productStore = productStore;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public void setRetailDocument(RetailDocument retailDocument) {
    this.retailDocument = retailDocument;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public void setTransactionHashCode(String transactionHashCode) {
    this.transactionHashCode = transactionHashCode;
  }

  public void setTransactionId(UUID transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductStoreTransaction [transactionId=");
    builder.append(transactionId);
    builder.append(", transactionHashCode=");
    builder.append(transactionHashCode);
    builder.append(", productStore=");
    builder.append(productStore);
    builder.append(", transactionDate=");
    builder.append(transactionDate);
    builder.append(", outstandingQuantity=");
    builder.append(outstandingQuantity);
    builder.append(", quantity=");
    builder.append(quantity);
    builder.append(", retailDocument=");
    builder.append(retailDocument);
    builder.append("]");
    return builder.toString();
  }

}
