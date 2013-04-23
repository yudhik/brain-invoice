package com.brainmaster.apps.invoicing.ejb.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.brainmaster.apps.invoicing.ejb.IntegrationModelRepositoryBean;
import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;

public class IntegrationModelTest extends Arquillian {
	
	private static final Logger log = LoggerFactory.getLogger(IntegrationModelTest.class);

	private static final String EMAIL_ID = "mail@yahoo.com";
	private static final String BRAND_NAME = "ABC";
	private static final String CATEGORY_NAME = "CBA";
	private static final String PACKAGING_CODE = "KG";

	@Inject
	private IntegrationModelRepositoryBean repositoryBean;
	
	
	@Deployment
	public static JavaArchive createTestArchive() {
		log.info("creating archive for test");
		return ShrinkWrap.create(JavaArchive.class, "IntegrationModelTest.jar")
				.addPackages(true, "com.brainmaster.apps.invoicing.model", "com.brainmaster.util.formatter", 
						"com.brainmaster.util.helper.uuid", "com.brainmaster.util.types")
				.addClasses(IntegrationModelRepositoryBean.class)
				.addAsManifestResource("META-INF/persistence-test.xml", "persistence.xml")
//				.addAsManifestResource("test-ds.xml", "test-ds.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void testIntegrationRepositoryBeanInjection() {
		Assert.assertNotNull(repositoryBean, "ejb is null");
		log.info("successsfully injecting ejb");
	}
	
	@Test(dependsOnMethods = "testIntegrationRepositoryBeanInjection")
	public void testCreatingAccount() {
		Account account = repositoryBean.saveAccount(new Account(EMAIL_ID, "ucup", "sanusi"));
		Assert.assertNotNull(account.getAccountId(), "account is null");
		log.info(account.toString());
	}
	
	@Test(dependsOnMethods={"testCreatingAccount"})
	public void testCreatingBrand() {
		Account account = repositoryBean.getAccountWithEmail(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Brand brand = repositoryBean.saveBrand(new Brand(account, BRAND_NAME));
		Assert.assertNotNull(brand.getKeys().getBrandName(), "brand name is null");
		log.info(brand.toString());
	}
	
	@Test(dependsOnMethods={"testCreatingBrand"})
	public void testCreatingCategory() {
		Account account = repositoryBean.getAccountWithEmail(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Category category = repositoryBean.saveCategory(new Category(account, CATEGORY_NAME));
		Assert.assertNotNull(category.getKeys().getCategoryName(), "category name is null");
		log.info(category.toString());
	}
	
	@Test(dependsOnMethods={"testCreatingBrand"})
	public void testCreatingCategoryChild() {
		Account account = repositoryBean.getAccountWithEmail(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Category category = new Category(account, CATEGORY_NAME + "A");
//		Category brand = repositoryBean.getCategoryFromKey(new CategoryAccountKeys(account, CATEGORY_NAME + "A"));
		Category categoryChild = repositoryBean.saveCategory(new Category(account, CATEGORY_NAME + "A1", category));
		Assert.assertNotNull(category.getKeys().getCategoryName(), "parent category name is null");
		Assert.assertNotNull(categoryChild.getKeys().getCategoryName(), "parent category name is null");
		log.info(category.toString());
		log.info(categoryChild.toString());
		
	}
	
	@Test(dependsOnMethods={"testCreatingCategory"})
	public void testCreatingPackaging() {
		Account account = repositoryBean.getAccountWithEmail(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		PackagingUnit packagingUnit = repositoryBean.savePackaging(new PackagingUnit(new PackagingAccountKeys(account, PACKAGING_CODE), "Kilogram"));
		Assert.assertNotNull(packagingUnit.getKeys().getPackagingId(), "packaging name is null");
		log.info(packagingUnit.toString());
	}
	
	@Test(dependsOnMethods={"testCreatingPackaging"})
	public void testCreatingProduct() {
		Account account = repositoryBean.getAccountWithEmail(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Brand brand = repositoryBean.getBrandFromKey(new BrandAccountKeys(account, BRAND_NAME));
		Category category = repositoryBean.getCategoryFromKey(new CategoryAccountKeys(account, CATEGORY_NAME));
		PackagingUnit packaging = repositoryBean.getPackagingFromKey(new PackagingAccountKeys(account, PACKAGING_CODE));
		Product product = repositoryBean.saveProduct(new Product(account, "A10001", "Barang Terlarang", "1001", brand, category, packaging));
		Assert.assertNotNull(product.getKeys().getProductCode(), "product code is null");
		log.info(product.toString());
	}
	
}
