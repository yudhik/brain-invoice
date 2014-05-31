package com.brainmaster.apps.invoicing.authorization;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Hibernate;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.internal.IdentityManagerFactory;
import org.picketlink.idm.model.Realm;
import org.picketlink.idm.model.SimpleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;


@Singleton
@Startup
public class IdentityManagerInitializer {

  private static final Logger log = LoggerFactory.getLogger(IdentityManagerInitializer.class);

  @Inject
  private IdentityManagerFactory identityManagerFactory;

  @Inject
  private EntityManager em;

  @PostConstruct
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void initialize() {
    List<Account> accounts = em.createNamedQuery("all-account", Account.class).getResultList();
    for (Account account : accounts) {
      Realm realm = new Realm(account.getAccountId());
      IdentityManager identityManager = identityManagerFactory.createIdentityManager(realm);
      Hibernate.initialize(account.getUsers());
      for (User user : account.getUsers()) {
        org.picketlink.idm.model.User userModel = new SimpleUser(user.getUsername());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmailAddress());
        Password password = new Password(new String(ArrayUtils.toPrimitive(user.getPassword())));
      }
    }
  }

}
