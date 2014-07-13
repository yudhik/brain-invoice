package com.brainmaster.apps.invoicing.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;

@Stateless
public class AccountServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public Account addUserFromAccount(UUID accountUuid, User user) throws Exception {
    log.debug("add user from account : {}, user : {}", new Object[] {accountUuid, user});
    Account account = getAccountFromKey(accountUuid);
    Hibernate.initialize(account.getUsers());
    account.getUsers().add(user);
    account = save(account);
    return account;
  }

  public void delete(Account account) throws Exception {
    log.debug("deleting account : {}", account);
    Account savedAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    if (savedAccount != null) {
      getEntityManager().remove(savedAccount);
    } else {
      throw new EntityNotFoundException("can not delete unsaved account : " + account);
    }
    getEntityManager().flush();
  }

  public Account getAccountFromKey(UUID accountUuid) {
    log.debug("get account from key : {}", new Object[] {accountUuid});
    return getEntityManager().find(Account.class, accountUuid);
  }

  public List<Account> getAllAccount() {
    log.debug("get all account");
    return getEntityManager().createQuery("from Account", Account.class).getResultList();
  }

  public Account save(Account account) throws Exception {
    log.debug("saving account : {}", new Object[] {account});
    Account savedAccount = getAccountFromKey(account.getAccountUuid());
    if (savedAccount != null) {
      BeanUtils.copyProperties(savedAccount, account);
      account = getEntityManager().merge(savedAccount);
    } else {
      getEntityManager().persist(account);
    }
    getEntityManager().flush();
    return account;
  }
}
