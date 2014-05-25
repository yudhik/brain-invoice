package com.brainmaster.apps.invoicing.core.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "retail_document_content")
public class RetailDocumentContent implements Serializable {

  private static final long serialVersionUID = -7449253635899853567L;

  @Id
  @Type(type = "uuid")
  @Column(length = DatabaseColumnConstant.SIZE_UUID)
  private UUID uuid;

  @Lob
  @Column(nullable = false)
  @ColumnTransformer(read = "UNCOMPRESS(data)", write = "COMPRESS(?)")
  private Byte[] data;

  @OneToOne
  private RetailDocument summary;

  public RetailDocumentContent() {}

  public RetailDocumentContent(UUID uuid, Byte[] data) {
    this.uuid = uuid;
    this.data = data;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RetailDocumentContent other = (RetailDocumentContent) obj;
    if (summary == null) {
      if (other.summary != null)
        return false;
    } else if (!summary.equals(other.summary))
      return false;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }

  public Byte[] getData() {
    return data;
  }

  @Transient
  public Source getDataAsSource() throws Exception {
    return new StreamSource(new InputStreamReader(new BufferedInputStream(new ByteArrayInputStream(
        ArrayUtils.toPrimitive(data)))));
  }

  public RetailDocument getSummary() {
    return summary;
  }

  public UUID getUuid() {
    return uuid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((summary == null) ? 0 : summary.hashCode());
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
    return result;
  }

  public void setData(Byte[] data) {
    this.data = data;
  }

  public void setSummary(RetailDocument summary) {
    this.summary = summary;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RetailDocumentContent [uuid=");
    builder.append(uuid);
    builder.append("]");
    return builder.toString();
  }

}
