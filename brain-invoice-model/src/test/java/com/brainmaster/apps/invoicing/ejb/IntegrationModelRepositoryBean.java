package com.brainmaster.apps.invoicing.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.model.id.ProductAccountKeys;


@Stateless
public class IntegrationModelRepositoryBean {
	
	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account saveAccount(Account account) {
		em.persist(account);
		return account;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category saveCategory(Category category) {
		em.persist(category);
		return category;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Brand saveBrand(Brand brand) {
		em.persist(brand);
		return brand;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PackagingUnit savePackaging(PackagingUnit packaging) {
		em.persist(packaging);
		return packaging;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Product saveProduct(Product product) {
		em.persist(product);
		return product;
	}
	
	public Account getAccountWithEmail(String email) {
		return em.createNamedQuery("account-with-email", Account.class).setParameter("email", email).getSingleResult();
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

	public List<Account> getAllAccount() {
		return em.createQuery("from Account", Account.class).getResultList();
	}

	public List<Brand> getAllBrand() {
		return em.createQuery("from Brand", Brand.class).getResultList();
	}

	public List<Category> getAllCategory() {
		return em.createQuery("from Category", Category.class).getResultList();
	}
	
	public List<PackagingUnit> getAllPackaging() {
		return em.createQuery("from Packaging", PackagingUnit.class).getResultList();
	}

}
