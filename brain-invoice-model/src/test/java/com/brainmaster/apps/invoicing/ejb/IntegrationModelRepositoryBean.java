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

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.ProductStore;
import com.brainmaster.apps.invoicing.model.Role;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.apps.invoicing.model.StoreDetail;
import com.brainmaster.apps.invoicing.model.User;
import com.brainmaster.apps.invoicing.model.UserRole;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.model.id.UserRoleKeys;

@Stateless
public class IntegrationModelRepositoryBean {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account save(Account account) {
	em.persist(account);
	em.flush();
	return account;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Category save(Category category) {
	em.persist(category);
	em.flush();
	return category;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Brand save(Brand brand) {
	em.persist(brand);
	em.flush();
	return brand;
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Role save(Role role) {
	em.persist(role);
	em.flush();
	return role;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User createUserWithRoles(User user, List<Role> roles) {
	for (Role role : roles) {
	    user = getUser(user.getEmailAddress());
	    user.getUserRoles().add(new UserRole(new UserRoleKeys(user, role)));
	}
	user = save(user);
	Hibernate.initialize(user.getUserRoles());
	return user;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Store createProductForStore(Store store, List<Product> products) {
	for (Product product : products) {
	    store = getStoreFromKey(store.getStoreId(), true, true);
	    store.getProducts().add(
		    new ProductStore(store.getAccount(), product, store));
	}
	store = save(store);
	Hibernate.initialize(store.getProducts());
	return store;
    }

    public User getUser(String email) {
	return em.createNamedQuery("user-with-email", User.class)
		.setParameter("email", email).getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account getAccountFromKey(UUID accountUuid) {
	return em.find(Account.class, accountUuid);
    }

    public Brand getBrandFromKey(BrandAccountKeys brandAccountKeys) {
	return em.find(Brand.class, brandAccountKeys);
    }

    public Brand getReference(BrandAccountKeys brandAccountKeys) {
	return em.getReference(Brand.class, brandAccountKeys);
    }

    public Category getCategoryFromKey(CategoryAccountKeys categoryAccountKeys) {
	return em.find(Category.class, categoryAccountKeys);
    }

    public Category getReference(CategoryAccountKeys categoryAccountKeys) {
	return em.getReference(Category.class, categoryAccountKeys);
    }

    public PackagingUnit getPackagingFromKey(
	    PackagingAccountKeys packagingAccountKeys) {
	return em.find(PackagingUnit.class, packagingAccountKeys);
    }

    public PackagingUnit getReference(PackagingAccountKeys packagingAccountKeys) {
	return em.getReference(PackagingUnit.class, packagingAccountKeys);
    }

    public Product getProductFromCode(Account account, String productCode) {
	assert (account != null && !StringUtils.isEmpty(productCode));
	return em.createNamedQuery("product-with-code", Product.class)
		.setParameter("account", account)
		.setParameter("productCode", productCode).getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Store getStoreFromKey(UUID storeAccountKeys) {
	return getStoreFromKey(storeAccountKeys, false);
    }

    public Role getRoleFromKey(String roleId) {
	return em.find(Role.class, roleId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Store getStoreFromKey(UUID storeId, boolean fetchChild) {
	return getStoreFromKey(storeId, true, false);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Store getStoreFromKey(UUID storeId, boolean fetchChild,
	    boolean fetchUser) {
	assert (em != null);
	Store store = em.find(Store.class, storeId);
	assert (store != null);
	if (fetchChild)
	    Hibernate.initialize(store.getChildStores());
	if (fetchUser)
	    Hibernate.initialize(store.getUsers());
	return store;
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
}