package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Stateless
public class ProductServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public List<Product> getAllProductFromAccount(Account account) {
    log.debug("get all product from account : {}", new Object[] {account});
    Account myAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getProducts());
    return myAccount.getProducts();
  }
}
