package com.brainmaster.apps.invoicing.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;

@Stateless
public class AccountServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public Account addUserFromAccount(UUID accountUuid, User user) {
    log.debug("add user from account : {}, user : {}", new Object[] {accountUuid, user});
    Account account = getAccountFromKey(accountUuid);
    Hibernate.initialize(account.getUsers());
    account.getUsers().add(user);
    account = save(account);
    return account;
  }

  public Account getAccountFromKey(UUID accountUuid) {
    log.debug("get account from key : {}", new Object[] {accountUuid});
    return getEntityManager().find(Account.class, accountUuid);
  }

  public List<Account> getAllAccount() {
    log.debug("get all account");
    return getEntityManager().createQuery("from Account", Account.class).getResultList();
  }

  public Account save(Account account) {
    log.debug("saving account : {}", new Object[] {account});
    getEntityManager().persist(account);
    getEntityManager().flush();
    return account;
  }
}
