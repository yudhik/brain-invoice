package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.ProductStore;
import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;

@Stateless
public class ProductServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  @EJB
  private StoreServiceBean storeServiceBean;

  public Store createProductForStore(Store store, List<Product> products, User createBy)
      throws Exception {
    for (Product product : products) {
      store = storeServiceBean.getStoreFromKey(store.getStoreId(), true, true);
      store.getProducts().add(new ProductStore(store.getAccount(), product, store, createBy));
    }
    store = storeServiceBean.save(store);
    Hibernate.initialize(store.getProducts());
    return store;
  }

  public List<Product> getAllProductFromAccount(Account account) {
    log.debug("get all product from account : {}", new Object[] {account});
    Account myAccount = getEntityManager().find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getProducts());
    return myAccount.getProducts();
  }

  public Product getProductFromCode(Account account, String productCode) {
    assert (account != null && !StringUtils.isEmpty(productCode));
    return getEntityManager().createNamedQuery("product-with-code", Product.class)
        .setParameter("account", account).setParameter("productCode", productCode)
        .getSingleResult();
  }

  public Product save(Product product) throws Exception {
    Product savedProduct = getProductFromCode(product.getAccount(), product.getProductCode());
    if (savedProduct != null) {
      BeanUtils.copyProperties(savedProduct, product);
      product = getEntityManager().merge(savedProduct);
    } else {
      getEntityManager().persist(product);
    }
    getEntityManager().flush();
    return product;
  }
}
