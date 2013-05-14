package com.brainmaster.apps.invoicing.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.apps.invoicing.model.StoreDetail;
import com.brainmaster.apps.invoicing.model.User;
import com.brainmaster.apps.invoicing.model.UserStore;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.model.id.ProductAccountKeys;
import com.brainmaster.apps.invoicing.model.id.StoreAccountKeys;


@Stateless
public class IntegrationModelRepositoryBean {
	
	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account saveAccount(Account account) {
		em.persist(account);
		em.flush();
		return account;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category saveCategory(Category category) {
		em.persist(category);
		em.flush();
		return category;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Brand saveBrand(Brand brand) {
		em.persist(brand);
		em.flush();
		return brand;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PackagingUnit savePackaging(PackagingUnit packaging) {
		em.persist(packaging);
		em.flush();
		return packaging;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Product saveProduct(Product product) {
		em.persist(product);
		em.flush();
		return product;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Store saveStore(Store store) {
		em.persist(store);
		em.flush();
		return store;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User saveUser(User user) {
		em.persist(user);
		em.flush();
		return user;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public StoreDetail saveStoreDetail(StoreDetail storeDetail) {
		em.persist(storeDetail);
		em.flush();
		return storeDetail;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UserStore saveUserStore(UserStore userStore) {
		em.persist(userStore);
		em.flush();
		return userStore;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Store addUserToStore(Store store, List<UserStore> userStoreList) {
		Store mystore = em.find(Store.class, store.getKeys());
		for(UserStore userStore : userStoreList) {
			mystore.getUserStoreList().add(userStore);
		}
		em.persist(mystore);
		em.flush();
		return mystore;
	}
	
	public User getUser(String email) {
		return em.createNamedQuery("user-with-email", User.class).setParameter("email", email).getSingleResult();
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
	
	public Product getProductFromKey(ProductAccountKeys productAccountKeys) {
		return em.find(Product.class, productAccountKeys);
	}
	
	public Store getStoreFromKey(StoreAccountKeys storeAccountKeys) {
		return getStoreFromKey(storeAccountKeys, false);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Store getStoreFromKey(StoreAccountKeys storeAccountKeys, boolean fetchChild) {
		Store store = em.find(Store.class, storeAccountKeys);
		if(fetchChild)
			Hibernate.initialize(store.getChildStores());
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
	public List<User> getUsersFromStore(Store store) {
//		Account myAccount = em.find(Account.class, store.getKeys().getAccount().getAccountUuid());
//		StoreAccountKeys storeAccountKeys = new StoreAccountKeys(myAccount, store.getKeys().getUuid());
		Store myStore = em.find(Store.class, store.getKeys());
		Hibernate.initialize(myStore.getUserStoreList());
		List<User> users = new ArrayList<User>();
		for(UserStore userStore : myStore.getUserStoreList()) {
			users.add(userStore.getKeys().getUser());
		}
		return users;
	}
}
