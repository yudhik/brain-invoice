package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.PackagingUnit;
import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Stateless
public class PackagingServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public List<PackagingUnit> getAllPackagingFromAccount(Account account) {
    log.debug("get all packaging from account : {}", new Object[] {account});
    Account myAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getPackagingUnits());
    return myAccount.getPackagingUnits();
  }
}
