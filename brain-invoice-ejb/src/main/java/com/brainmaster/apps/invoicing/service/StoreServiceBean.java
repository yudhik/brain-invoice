package com.brainmaster.apps.invoicing.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Stateless
public class StoreServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public List<Store> getAllStoreFromAccount(Account account) {
    log.debug("get all store from account : {}", new Object[] {account});
    Account myAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getStores());
    return myAccount.getStores();
  }

  public List<Store> getAllStoreFromAccountId(UUID accountId) {
    log.debug("get all store from account identifier : {}", new Object[] {accountId});
    Account myAccount = getEntityManager().find(Account.class, accountId);
    Hibernate.initialize(myAccount.getStores());
    return myAccount.getStores();
  }
}
