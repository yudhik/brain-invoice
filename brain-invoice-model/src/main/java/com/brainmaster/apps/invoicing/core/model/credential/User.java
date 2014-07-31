package com.brainmaster.apps.invoicing.core.model.credential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;

import com.brainmaster.apps.invoicing.core.model.UserStore;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email_address",
    "account_id"}))
@NamedQueries({@NamedQuery(name = "user-with-email",
    query = "select a from User a where a.emailAddress = :email")})
public class User extends AbstractIdentityType implements org.picketlink.idm.model.Account {

  private static final long serialVersionUID = 6862888645550391162L;

  @Id
  @Type(type = "uuid")
  @Column(name = "user_id", length = DatabaseColumnConstant.SIZE_UUID)
  private UUID userId;

  @NotNull
  @Column(name = "username", unique = true)
  @AttributeProperty
  @Unique
  private String username;

  @NotNull
  @Email
  @Column(name = "email_address", unique = true)
  @Unique
  @AttributeProperty
  private String emailAddress;

  @NotNull
  @Column(name = "password")
  private Byte[] password;

  @NotBlank
  @Column(name = "first_name", length = DatabaseColumnConstant.SIZE_FIRSTNAME)
  @AttributeProperty
  private String firstName;

  @Column(name = "last_name", length = DatabaseColumnConstant.SIZE_LASTNAME)
  @AttributeProperty
  private String lastName;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserStore> userStores = new ArrayList<UserStore>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserRole> userRoles = new ArrayList<UserRole>();

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  public User() {}

  public User(UUID userId, String username, String emailAddress, Byte[] password, String firstName,
      String lastName, Account account) {
    this.userId = userId;
    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.account = account;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  public Account getAccount() {
    return account;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Byte[] getPassword() {
    return password;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  public List<UserStore> getUserStores() {
    return userStores;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(Byte[] password) {
    this.password = password;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  public void setUserStores(List<UserStore> userStores) {
    this.userStores = userStores;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("User [userId=").append(userId).append(", username=").append(username)
        .append(", emailAddress=").append(emailAddress).append(", password=")
        .append(Arrays.toString(password)).append(", firstName=").append(firstName)
        .append(", lastName=").append(lastName).append("]");
    return builder.toString();
  }

}
