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
import org.testng.annotations.Test;

import com.brainmaster.apps.invoicing.ejb.IntegrationModelRepositoryBean;
import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Brand;
import com.brainmaster.apps.invoicing.model.Category;
import com.brainmaster.apps.invoicing.model.PackagingUnit;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.Role;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.apps.invoicing.model.StoreDetail;
import com.brainmaster.apps.invoicing.model.User;
import com.brainmaster.apps.invoicing.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.model.ext.CompanyInformation;
import com.brainmaster.apps.invoicing.model.ext.StoreType;
import com.brainmaster.apps.invoicing.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.model.id.StoreAccountKeys;

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

    private static final UUID ACCOUNT_UUID = UUID.randomUUID();
    private static final UUID USER_ID = UUID.randomUUID();
    private static final UUID STORE_ID = UUID.randomUUID();

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
		.addAsWebInfResource(EmptyAsset.INSTANCE,
			ArchivePaths.create("beans.xml"))
		// .addAsWebInfResource("test-ds.xml")
		.addAsLibraries(
			DependencyResolvers
				.use(MavenDependencyResolver.class)
				.artifacts(
					"org.apache.commons:commons-lang3:3.1",
					"commons-codec:commons-codec:20041127.091804")
				.resolveAsFiles());
	war.writeTo(System.out,
		org.jboss.shrinkwrap.api.formatter.Formatters.VERBOSE);
	System.err.println();
	return war;
    }

    @Test
    public void testIntegrationRepositoryBeanInjection() {
	Assert.assertNotNull(repositoryBean, "ejb is null");
	log.info("successsfully injecting ejb");
    }

    @Test(dependsOnMethods = "testIntegrationRepositoryBeanInjection")
    public void testCreatingAccount() {
	Account account = new Account(ACCOUNT_UUID, EMAIL_ID, "ucup", "sanusi");
	repositoryBean.save(account);
	Assert.assertNotNull(account.getAccountId(), "account is null");
	log.info(account.toString());
    }

    @Test(dependsOnMethods = { "testCreatingAccount" })
    public void testCreatingBrand() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Brand brand = repositoryBean.save(new Brand(account, BRAND_NAME));
	Assert.assertNotNull(brand.getKeys().getBrandName(),
		"brand name is null");
	Assert.assertEquals(repositoryBean.getAllBrandFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 1);
	log.info(brand.toString());
    }

    @Test(dependsOnMethods = { "testCreatingBrand" })
    public void testCreatingCategory() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Assert.assertNotNull(account.getAccountId(), "account id is null");
	Category category = repositoryBean.save(new Category(account,
		CATEGORY_NAME));
	Assert.assertNotNull(category.getKeys().getCategoryName(),
		"category name is null");
	Assert.assertEquals(repositoryBean.getAllCategoryFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 1);
	log.info(category.toString());
    }

    @Test(dependsOnMethods = { "testCreatingCategory" })
    public void testCreatingCategoryChild() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Assert.assertNotNull(account.getAccountId(), "account id is null");
	Category category = new Category(account, CATEGORY_NAME + "A");
	Category categoryChild = repositoryBean.save(new Category(account,
		CATEGORY_NAME + "A1", category));
	Assert.assertNotNull(category.getKeys().getCategoryName(),
		"parent category name is null");
	Assert.assertNotNull(categoryChild.getKeys().getCategoryName(),
		"parent category name is null");
	Assert.assertEquals(repositoryBean.getAllCategoryFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 3);
	log.info(category.toString());
	log.info(categoryChild.toString());

    }

    @Test(dependsOnMethods = { "testCreatingCategory" })
    public void testCreatingPackaging() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Assert.assertNotNull(account.getAccountId(), "account id is null");
	PackagingUnit packagingUnit = repositoryBean.save(new PackagingUnit(
		new PackagingAccountKeys(account, PACKAGING_CODE), "Kilogram"));
	Assert.assertNotNull(packagingUnit.getKeys().getPackagingId(),
		"packaging name is null");
	Assert.assertEquals(repositoryBean.getAllPackagingFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 1);
	log.info(packagingUnit.toString());
    }

    @Test(dependsOnMethods = { "testCreatingPackaging" })
    public void testCreatingProduct() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Assert.assertNotNull(account.getAccountId(), "account id is null");
	Brand brand = repositoryBean.getBrandFromKey(new BrandAccountKeys(
		account, BRAND_NAME));
	Category category = repositoryBean
		.getCategoryFromKey(new CategoryAccountKeys(account,
			CATEGORY_NAME));
	PackagingUnit packaging = repositoryBean
		.getPackagingFromKey(new PackagingAccountKeys(account,
			PACKAGING_CODE));
	Product product = repositoryBean.save(new Product(account, "A10001",
		"Barang Terlarang", "1001", brand, category, packaging));
	Assert.assertNotNull(product.getKeys().getProductCode(),
		"product code is null");
	Assert.assertEquals(repositoryBean.getAllProductFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 1);
	log.info(product.toString());
    }

    @Test(dependsOnMethods = { "testCreatingProduct" })
    public void testCreatingStore() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	Assert.assertNotNull(account.getAccountId(), "account id is null");
	CompanyInformation companyInformation = new CompanyInformation(
		"Jl. Perjuangan Tiada Akhir", "Dwiwarna, Karang anyar",
		"Jakarta Pusat", "11111", "Jakarta", "Jakarta Utara",
		"Support@me.com", "123456", "123456", "123456");
	BankInformation bankInformation = new BankInformation("Paijo",
		"1234567", "bank miun", "cabang satusatunya", "1234",
		"Jl. Karang Anyar A", "Jakarta Pusat", "haloo", "123456",
		"Jakarta Pusat", "DKI Jakarta");
	Store store = new Store(account, STORE_ID, A_STORE_NAME,
		StoreType.BRANCH, "Hello", null);
	StoreDetail storeDetail = new StoreDetail(store, companyInformation,
		bankInformation);
	store.setStoreDetail(storeDetail);
	repositoryBean.save(store);
	Assert.assertEquals(repositoryBean.getAllStoreFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
		.size(), 1);
	log.info(store.toString());
	log.info("#store id : {}", new Object[] { STORE_ID });
    }

    @Test(dependsOnMethods = { "testCreatingStore" })
    public void testCreatingRole() {
	final String ANONYMOUS = "ANONYMOUS";
	Role role = repositoryBean.save(new Role(ANONYMOUS, "unknown user"));
	Assert.assertNotNull(repositoryBean.getRoleFromKey(role.getRoleId()),
		"role is not save");
    }

    @Test(dependsOnMethods = { "testCreatingRole" })
    public void testCreatingUserFromStore() {
	Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
	log.info("store id : {}", new Object[] { STORE_ID });
	StoreAccountKeys storeAccountKeys = new StoreAccountKeys(account,
		STORE_ID);
	Store store = repositoryBean.getReference(storeAccountKeys);
	User user = new User(USER_ID, "ucup.sanusi", EMAIL_ID, ArrayUtils
			.toObject(DigestUtils.md5Hex("simplePassword")
				.getBytes()), "ucup", "sanusi");
	user.setStore(store);
	repositoryBean.save(user);
	Assert.assertEquals(repositoryBean.getStoreFromKey(storeAccountKeys, false, true).getUsers().size(), 1);
    }
}
