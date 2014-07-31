package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "invoice_transaction_detail")
public class InvoiceTransactionDetail extends AbstractCreateByEntity implements Serializable {

  private static final long serialVersionUID = -5032646167147405958L;

  @Id
  @Type(type = "uuid")
  @Column(length = DatabaseColumnConstant.SIZE_UUID)
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(nullable = false, columnDefinition = "Decimal(25,2) default '0.00'")
  private BigDecimal price;

  @Column(name = "serial_number", nullable = false, columnDefinition = "varchar(255) default '-'")
  private String serialNumber;

  @Column(nullable = false, columnDefinition = "Decimal(7) default '0'")
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "invoice_id")
  private InvoiceTransaction invoiceTransaction;

  @Deprecated
  public InvoiceTransactionDetail() {
    super(null);
  }

  public InvoiceTransactionDetail(User createdBy) {
    super(createdBy);
  }

  public InvoiceTransactionDetail(User createdBy, UUID uuid, Product product, BigDecimal price,
      String serialNumber, Integer quantity) {
    super(createdBy);
    this.uuid = uuid;
    this.product = product;
    this.price = price;
    this.serialNumber = serialNumber;
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    InvoiceTransactionDetail other = (InvoiceTransactionDetail) obj;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }

  public InvoiceTransaction getInvoiceTransaction() {
    return invoiceTransaction;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public UUID getUuid() {
    return uuid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
    return result;
  }

  public void setInvoiceTransaction(InvoiceTransaction invoiceTransaction) {
    this.invoiceTransaction = invoiceTransaction;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("InvoiceTransactionDetail [uuid=");
    builder.append(uuid);
    builder.append(", product=");
    builder.append(product);
    builder.append(", price=");
    builder.append(price);
    builder.append(", serialNumber=");
    builder.append(serialNumber);
    builder.append(", quantity=");
    builder.append(quantity);
    builder.append(", invoiceTransaction=");
    builder.append(invoiceTransaction);
    builder.append("]");
    return builder.toString();
  }

}
