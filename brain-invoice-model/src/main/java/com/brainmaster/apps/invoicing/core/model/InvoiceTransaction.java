package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.ext.DocumentType;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "invoice_transaction")
public class InvoiceTransaction extends AbstractCreateByEntity implements Serializable {

  private static final long serialVersionUID = -8847812765453888216L;

  @Id
  @Type(type = "uuid")
  @Column(length = DatabaseColumnConstant.SIZE_UUID)
  private UUID uuid;

  @Column(name = "invoice_number", length = DatabaseColumnConstant.SIZE_DEFAULT)
  private String invoiceNumber;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "invoice_date")
  private Date invoiceDate;

  @Column(name = "grand_total")
  private BigDecimal grandTotal;

  @ManyToOne(targetEntity = Account.class)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(targetEntity = Store.class)
  @JoinColumn(name = "store_seller_id")
  private Store seller;

  @ManyToOne(targetEntity = Store.class)
  @JoinColumn(name = "store_buyer_id")
  private Store buyer;

  @Column(name = "document_type")
  private DocumentType documentType;

  @OneToMany(mappedBy = "invoiceTransaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<InvoiceTransactionDetail> transactions = new ArrayList<InvoiceTransactionDetail>();

  public InvoiceTransaction(User createdBy) {
    super(createdBy);
  }

  public InvoiceTransaction(User createdBy, UUID uuid, String invoiceNumber, Date invoiceDate,
      BigDecimal grandTotal, Account account, Store seller, Store buyer, DocumentType documentType) {
    super(createdBy);
    this.uuid = uuid;
    this.invoiceNumber = invoiceNumber;
    this.invoiceDate = invoiceDate;
    this.grandTotal = grandTotal;
    this.account = account;
    this.seller = seller;
    this.buyer = buyer;
    this.documentType = documentType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    InvoiceTransaction other = (InvoiceTransaction) obj;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }

  public Account getAccount() {
    return account;
  }

  public Store getBuyer() {
    return buyer;
  }

  public DocumentType getDocumentType() {
    return documentType;
  }

  public BigDecimal getGrandTotal() {
    return grandTotal;
  }

  public Date getInvoiceDate() {
    return invoiceDate;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public Store getSeller() {
    return seller;
  }

  public List<InvoiceTransactionDetail> getTransactions() {
    return transactions;
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

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setBuyer(Store buyer) {
    this.buyer = buyer;
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
  }

  public void setGrandTotal(BigDecimal grandTotal) {
    this.grandTotal = grandTotal;
  }

  public void setInvoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public void setSeller(Store seller) {
    this.seller = seller;
  }

  public void setTransactions(List<InvoiceTransactionDetail> transactions) {
    this.transactions = transactions;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("InvoiceTransaction [uuid=");
    builder.append(uuid);
    builder.append(", invoiceNumber=");
    builder.append(invoiceNumber);
    builder.append(", invoiceDate=");
    builder.append(invoiceDate);
    builder.append(", grandTotal=");
    builder.append(grandTotal);
    builder.append(", account=");
    builder.append(account);
    builder.append(", seller=");
    builder.append(seller);
    builder.append(", buyer=");
    builder.append(buyer);
    builder.append(", documentType=");
    builder.append(documentType);
    builder.append(", transactions=");
    builder.append(transactions);
    builder.append("]");
    return builder.toString();
  }

}
