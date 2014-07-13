package com.brainmaster.apps.invoicing.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.StoreDetail;
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

  public Store getStoreFromKey(UUID storeAccountKeys) {
    return getStoreFromKey(storeAccountKeys, false);
  }

  public Store getStoreFromKey(UUID storeId, boolean fetchChild) {
    return getStoreFromKey(storeId, true, false);
  }

  public Store getStoreFromKey(UUID storeId, boolean fetchChild, boolean fetchUser) {
    assert (getEntityManager() != null);
    Store store = getEntityManager().find(Store.class, storeId);
    assert (store != null);
    if (fetchChild)
      Hibernate.initialize(store.getChildStores());
    if (fetchUser)
      Hibernate.initialize(store.getUserStores());
    return store;
  }

  public Store save(Store store) throws Exception {
    Store savedStore = getStoreFromKey(store.getStoreId());
    if (savedStore != null) {
      BeanUtils.copyProperties(savedStore, store);
      store = getEntityManager().merge(savedStore);
    } else {
      getEntityManager().persist(store);
    }
    getEntityManager().flush();
    return store;
  }

  public StoreDetail saveStore(StoreDetail storeDetail) {
    getEntityManager().persist(storeDetail);
    getEntityManager().flush();
    return storeDetail;
  }
}
