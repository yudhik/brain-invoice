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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.ext.DocumentStatus;
import com.brainmaster.apps.invoicing.core.model.ext.DocumentType;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "retail_document")
public class RetailDocument extends AbstractCreateByEntity implements Serializable {

    private static final long serialVersionUID = 3384319381495087568L;

    @Id
    @Type(type = "uuid")
    @Column(name = "document_id", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID documentId;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Store sender;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Store recipient;

    @Column(length = DatabaseColumnConstant.SIZE_DEFAULT)
    private String reference;

    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_status")
    private DocumentStatus status;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_sent", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;

    @Column(name = "date_received", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReceived;

    @Column(name = "date_updated", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "summary")
    @PrimaryKeyJoinColumn
    private RetailDocumentContent content;

    @Deprecated
    public RetailDocument() {
	super(null);
    }
    
    public RetailDocument(Store sender, Store recipient, String reference,
	    DocumentType documentType, DocumentStatus status, Date dateCreated,
	    Date dateSent, Date dateReceived, Date dateUpdated,
	    RetailDocumentContent content, User createdBy) {
	super(createdBy);
	this.documentId = UUID.randomUUID();
	this.sender = sender;
	this.recipient = recipient;
	this.reference = reference;
	this.documentType = documentType;
	this.status = status;
	this.dateCreated = dateCreated;
	this.dateSent = dateSent;
	this.dateReceived = dateReceived;
	this.dateUpdated = dateUpdated;
	this.content = content;
    }

    public UUID getDocumentId() {
	return documentId;
    }

    public void setDocumentId(UUID documentId) {
	this.documentId = documentId;
    }
    
    @Transient
    public String getDocumentIdInString() {
	if (documentId != null)
	    return UUIDHelper.uuidToString(documentId);
	return null;
    }

    public Store getSender() {
	return sender;
    }

    public void setSender(Store sender) {
	this.sender = sender;
    }

    public Store getRecipient() {
	return recipient;
    }

    public void setRecipient(Store recipient) {
	this.recipient = recipient;
    }

    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    public DocumentType getDocumentType() {
	return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
	this.documentType = documentType;
    }

    public DocumentStatus getStatus() {
	return status;
    }

    public void setStatus(DocumentStatus status) {
	this.status = status;
    }

    public Date getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
    }

    public Date getDateSent() {
	return dateSent;
    }

    public void setDateSent(Date dateSent) {
	this.dateSent = dateSent;
    }

    public Date getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
	this.dateReceived = dateReceived;
    }

    public Date getDateUpdated() {
	return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
	this.dateUpdated = dateUpdated;
    }

    public RetailDocumentContent getContent() {
	return content;
    }

    public void setContent(RetailDocumentContent content) {
	this.content = content;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((documentId == null) ? 0 : documentId.hashCode());
	result = prime * result
		+ ((documentType == null) ? 0 : documentType.hashCode());
	result = prime * result
		+ ((recipient == null) ? 0 : recipient.hashCode());
	result = prime * result
		+ ((reference == null) ? 0 : reference.hashCode());
	result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
	RetailDocument other = (RetailDocument) obj;
	if (documentId == null) {
	    if (other.documentId != null)
		return false;
	} else if (!documentId.equals(other.documentId))
	    return false;
	if (documentType != other.documentType)
	    return false;
	if (recipient == null) {
	    if (other.recipient != null)
		return false;
	} else if (!recipient.equals(other.recipient))
	    return false;
	if (reference == null) {
	    if (other.reference != null)
		return false;
	} else if (!reference.equals(other.reference))
	    return false;
	if (sender == null) {
	    if (other.sender != null)
		return false;
	} else if (!sender.equals(other.sender))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("RetailDocument [documentId=");
	builder.append(getDocumentIdInString());
	builder.append(", sender=");
	builder.append(sender);
	builder.append(", recipient=");
	builder.append(recipient);
	builder.append(", reference=");
	builder.append(reference);
	builder.append(", documentType=");
	builder.append(documentType);
	builder.append(", status=");
	builder.append(status);
	builder.append(", dateCreated=");
	builder.append(dateCreated);
	builder.append(", dateSent=");
	builder.append(dateSent);
	builder.append(", dateReceived=");
	builder.append(dateReceived);
	builder.append(", dateUpdated=");
	builder.append(dateUpdated);
	builder.append("]");
	return builder.toString();
    }

}
