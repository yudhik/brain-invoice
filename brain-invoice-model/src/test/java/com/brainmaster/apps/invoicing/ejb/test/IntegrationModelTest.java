package com.brainmaster.apps.invoicing.ejb.test;

import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.brainmaster.apps.invoicing.ejb.IntegrationModelRepositoryBean;
import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.apps.invoicing.model.StoreDetail;
import com.brainmaster.apps.invoicing.model.User;
import com.brainmaster.apps.invoicing.model.UserStore;
import com.brainmaster.apps.invoicing.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.model.ext.CompanyInformation;
import com.brainmaster.apps.invoicing.model.ext.StoreType;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.model.id.StoreAccountKeys;
import com.brainmaster.apps.invoicing.model.id.UserAccountKeys;

public class IntegrationModelTest extends Arquillian {

	private static final Logger log = LoggerFactory
			.getLogger(IntegrationModelTest.class);

	private static final String EMAIL_ID = "mail@yahoo.com";
	private static final String BRAND_NAME = "ABC";
	private static final String CATEGORY_NAME = "CBA";
	private static final String PACKAGING_CODE = "KG";

	private static final String A_STORE_NAME = "My Store";

	@Inject
	private IntegrationModelRepositoryBean repositoryBean;
	
	private UUID userId;
	private UUID storeId;

	@Deployment
	public static Archive<?> createTestArchive() {
		log.info("creating archive for test");
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "IntegrationModelTest.war")
				.addPackages(true, "com.brainmaster.apps.invoicing.model",
						"com.brainmaster.util.formatter",
						"com.brainmaster.util.helper.uuid",
						"com.brainmaster.util.types")
				.addClasses(IntegrationModelRepositoryBean.class)
				.addAsResource("META-INF/persistence-test.xml",
						"META-INF/persistence.xml")
				// .addAsManifestResource("test-ds.xml", "test-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
				.addAsLibraries(
						DependencyResolvers
								.use(MavenDependencyResolver.class)
								.artifacts("org.apache.commons:commons-lang3:3.1", "commons-codec:commons-codec:20041127.091804")
								.resolveAsFiles());
		war.writeTo(System.out,
				org.jboss.shrinkwrap.api.formatter.Formatters.VERBOSE);
		System.err.println();
		return war;
	}
	
	@BeforeTest
	private void initUserId() {
		userId = UUID.randomUUID();
		storeId = UUID.randomUUID();
	}

	@Test
	public void testIntegrationRepositoryBeanInjection() {
		Assert.assertNotNull(repositoryBean, "ejb is null");
		Assert.assertNotNull(userId, "user id is null");
		log.info("successsfully injecting ejb");
	}

	@Test(dependsOnMethods = "testIntegrationRepositoryBeanInjection")
	public void testCreatingAccountAndUser() {
		Account account = new Account(EMAIL_ID,"ucup", "sanusi");
		UserAccountKeys keys = new UserAccountKeys(account, userId);
		account.getUsers().add(
				new User(keys, EMAIL_ID, ArrayUtils.toObject(
						DigestUtils.md5Hex("simplePassword").getBytes()), "ucup", "sanusi"));
		repositoryBean.saveAccount(account);
		User user = repositoryBean.getUser(EMAIL_ID);
		Assert.assertNotNull(account.getAccountId(), "account is null");
		log.info(account.toString());
		log.info(user.toString());
		Assert.assertNotNull(user.getKeys().getAccount().getAccountId(), "account is null");
		Assert.assertEquals(account.getUsers().size(), 1);
	}

	@Test(dependsOnMethods = { "testCreatingAccountAndUser" })
	public void testCreatingBrand() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Brand brand = repositoryBean.saveBrand(new Brand(account, BRAND_NAME));
		Assert.assertNotNull(brand.getKeys().getBrandName(),"brand name is null");
		Assert.assertEquals(repositoryBean.getAllBrandFromAccount(account).size(),1);
		log.info(brand.toString());
	}

	@Test(dependsOnMethods = { "testCreatingBrand" })
	public void testCreatingCategory() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Category category = repositoryBean.saveCategory(new Category(account,CATEGORY_NAME));
		Assert.assertNotNull(category.getKeys().getCategoryName(),"category name is null");
		Assert.assertEquals(repositoryBean.getAllCategoryFromAccount(account).size(), 1);
		log.info(category.toString());
	}

	@Test(dependsOnMethods = { "testCreatingCategory" })
	public void testCreatingCategoryChild() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Category category = new Category(account, CATEGORY_NAME + "A");
		Category categoryChild = repositoryBean.saveCategory(
				new Category(account, CATEGORY_NAME + "A1", category));
		Assert.assertNotNull(category.getKeys().getCategoryName(), "parent category name is null");
		Assert.assertNotNull(categoryChild.getKeys().getCategoryName(), "parent category name is null");
		Assert.assertEquals(repositoryBean.getAllCategoryFromAccount(account).size(), 3);
		log.info(category.toString());
		log.info(categoryChild.toString());

	}

	@Test(dependsOnMethods = { "testCreatingCategory" })
	public void testCreatingPackaging() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		PackagingUnit packagingUnit = repositoryBean.savePackaging(
				new PackagingUnit(
						new PackagingAccountKeys(account, PACKAGING_CODE), "Kilogram"));
		Assert.assertNotNull(packagingUnit.getKeys().getPackagingId(), "packaging name is null");
		Assert.assertEquals(repositoryBean.getAllPackagingFromAccount(account).size(), 1);
		log.info(packagingUnit.toString());
	}

	@Test(dependsOnMethods = { "testCreatingPackaging" })
	public void testCreatingProduct() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		Brand brand = repositoryBean.getBrandFromKey(
				new BrandAccountKeys(account, BRAND_NAME));
		Category category = repositoryBean.getCategoryFromKey(
				new CategoryAccountKeys(account, CATEGORY_NAME));
		PackagingUnit packaging = repositoryBean.getPackagingFromKey(
				new PackagingAccountKeys(account,PACKAGING_CODE));
		Product product = repositoryBean.saveProduct(
				new Product(account,"A10001", "Barang Terlarang", "1001", 
						brand, category,packaging));
		Assert.assertNotNull(product.getKeys().getProductCode(),
				"product code is null");
		Assert.assertEquals(repositoryBean.getAllProductFromAccount(account).size(), 1);
		log.info(product.toString());
	}
	
	@Test(dependsOnMethods = { "testCreatingProduct" })
	public void testCreatingStore() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		Assert.assertNotNull(account.getAccountId(), "account id is null");
		CompanyInformation companyInformation = new CompanyInformation("Jl. Perjuangan Tiada Akhir", "Dwiwarna, Karang anyar", "Jakarta Pusat", "11111", "Jakarta", "Jakarta Utara", "Support@me.com", "123456", "123456", "123456");
		BankInformation bankInformation = new BankInformation("Paijo", "1234567", "bank miun", "cabang satusatunya", "1234", "Jl. Karang Anyar A", "Jakarta Pusat", "haloo", "123456", "Jakarta Pusat", "DKI Jakarta");
		Store store = repositoryBean.saveStore(new Store(account, storeId, A_STORE_NAME, StoreType.BRANCH, "Hello", null));
		StoreDetail storeDetail = repositoryBean.saveStoreDetail(new StoreDetail(store, companyInformation, bankInformation));
		Assert.assertNotNull(storeDetail, "store detail is null");
		Assert.assertEquals(repositoryBean.getAllStoreFromAccount(account).size(), 1);
		log.info(storeDetail.toString());
	}
	
	@Test(dependsOnMethods = { "testCreatingStore" })
	public void testCreatingUserStore() {
		User user = repositoryBean.getUser(EMAIL_ID);
		Account account = user.getKeys().getAccount();
		StoreAccountKeys storeAccountKeys = new StoreAccountKeys(account, storeId);
		Store store = repositoryBean.getStoreFromKey(storeAccountKeys);
		store.getUserStoreList().add(new UserStore(user,store));
		Assert.assertEquals(repositoryBean.getUsersFromStore(store).size(), 1);
	}

}
