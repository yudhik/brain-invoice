package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.User;

@Entity
@Table(name = "product_store_calculation_log")
public class ProductStoreCalculationLog extends AbstractCreateByEntity implements Serializable {

  private static final long serialVersionUID = -1981227504579173653L;

  @Id
  @Type(type = "uuid")
  @Column(name = "calculation_log_id")
  private UUID calculationLogId;

  @ManyToOne(targetEntity = ProductStoreTransaction.class)
  private ProductStoreTransaction latestProductStoreTransaction;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "calculation_time")
  private Date calculationTime;

  @Column(name = "original_quantity")
  private Long originalQuantity;

  @Column(name = "mathematic_operand")
  private char mathematicOperand;

  @Column
  private Long result;

  public ProductStoreCalculationLog(
      User createdBy,
      UUID calculationLogId,
      com.brainmaster.apps.invoicing.core.model.ProductStoreTransaction latestProductStoreTransaction,
      Date calculationTime, Long originalQuantity, char mathematicOperand, Long result) {
    super(createdBy);
    this.calculationLogId = calculationLogId;
    this.latestProductStoreTransaction = latestProductStoreTransaction;
    this.calculationTime = calculationTime;
    this.originalQuantity = originalQuantity;
    this.mathematicOperand = mathematicOperand;
    this.result = result;
  }

  public UUID getCalculationLogId() {
    return calculationLogId;
  }

  public Date getCalculationTime() {
    return calculationTime;
  }

  public ProductStoreTransaction getLatestProductStoreTransaction() {
    return latestProductStoreTransaction;
  }

  public char getMathematicOperand() {
    return mathematicOperand;
  }

  public Long getOriginalQuantity() {
    return originalQuantity;
  }

  public Long getResult() {
    return result;
  }

  public void setCalculationLogId(UUID calculationLogId) {
    this.calculationLogId = calculationLogId;
  }

  public void setCalculationTime(Date calculationTime) {
    this.calculationTime = calculationTime;
  }

  public void setLatestProductStoreTransaction(ProductStoreTransaction latestProductStoreTransaction) {
    this.latestProductStoreTransaction = latestProductStoreTransaction;
  }

  public void setMathematicOperand(char mathematicOperand) {
    this.mathematicOperand = mathematicOperand;
  }

  public void setOriginalQuantity(Long originalQuantity) {
    this.originalQuantity = originalQuantity;
  }

  public void setResult(Long result) {
    this.result = result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductStoreCalculationLog [calculationLogId=");
    builder.append(calculationLogId);
    builder.append(", latestProductStoreTransaction=");
    builder.append(latestProductStoreTransaction);
    builder.append(", calculationTime=");
    builder.append(calculationTime);
    builder.append(", originalQuantity=");
    builder.append(originalQuantity);
    builder.append(", mathematicOperand=");
    builder.append(mathematicOperand);
    builder.append(", result=");
    builder.append(result);
    builder.append("]");
    return builder.toString();
  }

}
