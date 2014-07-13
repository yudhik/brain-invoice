package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.PackagingUnit;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.id.PackagingAccountKeys;

@Stateless
public class PackagingServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public void delete(PackagingUnit packagingUnit) throws Exception {
    log.debug("deleting packaging unit : {}", packagingUnit);
    PackagingUnit savedPackagingUnit =
        getEntityManager().find(PackagingUnit.class, packagingUnit.getKeys());
    if (savedPackagingUnit != null) {
      getEntityManager().remove(savedPackagingUnit);
    } else {
      throw new EntityNotFoundException("can not delete unsaved packaging unit : " + packagingUnit);
    }
    getEntityManager().flush();
  }

  public List<PackagingUnit> getAllPackagingFromAccount(Account account) {
    log.debug("get all packaging from account : {}", new Object[] {account});
    Account myAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getPackagingUnits());
    return myAccount.getPackagingUnits();
  }

  public PackagingUnit getPackagingFromKey(PackagingAccountKeys packagingAccountKeys) {
    return getEntityManager().find(PackagingUnit.class, packagingAccountKeys);
  }

  public PackagingUnit save(PackagingUnit packaging) throws Exception {
    PackagingUnit savedPackagingUnit = getPackagingFromKey(packaging.getKeys());
    if (savedPackagingUnit != null) {
      BeanUtils.copyProperties(savedPackagingUnit, packaging);
      packaging = getEntityManager().merge(savedPackagingUnit);
    } else {
      getEntityManager().persist(packaging);
    }
    getEntityManager().flush();
    return packaging;
  }
}
