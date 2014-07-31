package com.brainmaster.apps.invoicing.ejb;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import com.brainmaster.apps.invoicing.core.model.Brand;
import com.brainmaster.apps.invoicing.core.model.Category;
import com.brainmaster.apps.invoicing.core.model.PackagingUnit;
import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.ProductStore;
import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.StoreDetail;
import com.brainmaster.apps.invoicing.core.model.UserStore;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.Role;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.credential.UserRole;
import com.brainmaster.apps.invoicing.core.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.core.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.core.model.id.PackagingAccountKeys;

@Stateless
public class IntegrationModelRepositoryBean {

  @PersistenceContext
  private EntityManager em;

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Account addUserFromAccount(UUID accountUuid, User user) {
    Account account = getAccountFromKey(accountUuid);
    Hibernate.initialize(account.getUsers());
    account.getUsers().add(user);
    account = save(account);
    return account;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store addUserFromStore(UUID storeId, User user) {
    Store store = getStoreFromKey(storeId);
    // user = getUser(user.getEmailAddress(), true);
    store.getUserStores().add(new UserStore(user, store));
    store = save(store);
    Hibernate.initialize(store.getUserStores());
    return store;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store createProductForStore(Store store, List<Product> products, User createBy) {
    for (Product product : products) {
      store = getStoreFromKey(store.getStoreId(), true, true);
      store.getProducts().add(new ProductStore(store.getAccount(), product, store, createBy));
    }
    store = save(store);
    Hibernate.initialize(store.getProducts());
    return store;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public User createUserWithRoles(User user, List<Role> roles) {
    for (Role role : roles) {
      user = getUser(user.getEmailAddress(), false);
      user.getUserRoles().add(new UserRole(user, role));
    }
    user = save(user);
    Hibernate.initialize(user.getUserRoles());
    return user;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Account getAccountFromKey(UUID accountUuid) {
    return em.find(Account.class, accountUuid);
  }

  public List<Account> getAllAccount() {
    return em.createQuery("from Account", Account.class).getResultList();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<Brand> getAllBrandFromAccount(Account account) {
    Account myAccount = em.find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getBrands());
    return myAccount.getBrands();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<Category> getAllCategoryFromAccount(Account account) {
    Account myAccount = em.find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getCategories());
    return myAccount.getCategories();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<PackagingUnit> getAllPackagingFromAccount(Account account) {
    Account myAccount = em.find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getPackagingUnits());
    return myAccount.getPackagingUnits();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<Product> getAllProductFromAccount(Account account) {
    Account myAccount = em.find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getProducts());
    return myAccount.getProducts();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<Store> getAllStoreFromAccount(Account account) {
    Account myAccount = em.find(Account.class, account.getAccountUuid());
    Hibernate.initialize(myAccount.getStores());
    return myAccount.getStores();
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<Store> getAllStoreFromAccountId(UUID accountId) {
    Account myAccount = em.find(Account.class, accountId);
    Hibernate.initialize(myAccount.getStores());
    return myAccount.getStores();
  }

  public Brand getBrandFromKey(BrandAccountKeys brandAccountKeys) {
    return em.find(Brand.class, brandAccountKeys);
  }

  public Category getCategoryFromKey(CategoryAccountKeys categoryAccountKeys) {
    return em.find(Category.class, categoryAccountKeys);
  }

  public PackagingUnit getPackagingFromKey(PackagingAccountKeys packagingAccountKeys) {
    return em.find(PackagingUnit.class, packagingAccountKeys);
  }

  public Product getProductFromCode(Account account, String productCode) {
    assert (account != null && !StringUtils.isEmpty(productCode));
    return em.createNamedQuery("product-with-code", Product.class).setParameter("account", account)
        .setParameter("productCode", productCode).getSingleResult();
  }

  public Brand getReference(BrandAccountKeys brandAccountKeys) {
    return em.getReference(Brand.class, brandAccountKeys);
  }

  public Category getReference(CategoryAccountKeys categoryAccountKeys) {
    return em.getReference(Category.class, categoryAccountKeys);
  }

  public PackagingUnit getReference(PackagingAccountKeys packagingAccountKeys) {
    return em.getReference(PackagingUnit.class, packagingAccountKeys);
  }

  public Role getRoleFromKey(String roleId) {
    return em.find(Role.class, roleId);
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store getStoreFromKey(UUID storeAccountKeys) {
    return getStoreFromKey(storeAccountKeys, false);
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store getStoreFromKey(UUID storeId, boolean fetchChild) {
    return getStoreFromKey(storeId, true, false);
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store getStoreFromKey(UUID storeId, boolean fetchChild, boolean fetchUser) {
    assert (em != null);
    Store store = em.find(Store.class, storeId);
    assert (store != null);
    if (fetchChild)
      Hibernate.initialize(store.getChildStores());
    if (fetchUser)
      Hibernate.initialize(store.getUserStores());
    return store;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public User getUser(String email, boolean fetchAccount) {
    User user =
        em.createNamedQuery("user-with-email", User.class).setParameter("email", email)
            .getSingleResult();
    if (user != null && fetchAccount) {
      Hibernate.initialize(user.getAccount());
    }
    return user;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Account save(Account account) {
    em.persist(account);
    em.flush();
    return account;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Brand save(Brand brand) {
    em.persist(brand);
    em.flush();
    return brand;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Category save(Category category) {
    em.persist(category);
    em.flush();
    return category;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public PackagingUnit save(PackagingUnit packaging) {
    em.persist(packaging);
    em.flush();
    return packaging;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Product save(Product product) {
    em.persist(product);
    em.flush();
    return product;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Role save(Role role) {
    em.persist(role);
    em.flush();
    return role;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Store save(Store store) {
    em.persist(store);
    em.flush();
    return store;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public User save(User user) {
    em.persist(user);
    em.flush();
    return user;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public StoreDetail saveStore(StoreDetail storeDetail) {
    em.persist(storeDetail);
    em.flush();
    return storeDetail;
  }

}
