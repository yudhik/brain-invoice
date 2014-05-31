package com.brainmaster.apps.invoicing.ejb.test;

import java.util.ArrayList;
import java.util.List;
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
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.brainmaster.apps.invoicing.core.model.Brand;
import com.brainmaster.apps.invoicing.core.model.Category;
import com.brainmaster.apps.invoicing.core.model.PackagingUnit;
import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.StoreDetail;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.Role;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.core.model.ext.CompanyInformation;
import com.brainmaster.apps.invoicing.core.model.ext.StoreType;
import com.brainmaster.apps.invoicing.core.model.id.BrandAccountKeys;
import com.brainmaster.apps.invoicing.core.model.id.CategoryAccountKeys;
import com.brainmaster.apps.invoicing.core.model.id.PackagingAccountKeys;
import com.brainmaster.apps.invoicing.ejb.IntegrationModelRepositoryBean;

public class IntegrationModelTest extends Arquillian {

  private static final Logger log = LoggerFactory.getLogger(IntegrationModelTest.class);

  private static final String EMAIL_ID = "mail@yahoo.com";
  private static final String EMAIL_ACCOUNT_ID = "blabla@mail.com";
  private static final String BRAND_NAME = "ABC";
  private static final String CATEGORY_NAME = "CBA";
  private static final String PACKAGING_CODE = "KG";
  private static final String A_STORE_NAME = "My Store";
  private static final String ANONYMOUS_ROLE = "ANONYMOUS";
  private static final String USER_ROLE = "USER";
  private static final String PRODUCT_CODE1 = "A10001";
  private static final String PRODUCT_CODE2 = "A10002";

  @Inject
  private IntegrationModelRepositoryBean repositoryBean;

  private static final UUID ACCOUNT_UUID = UUID.randomUUID();
  private static final UUID USER_ID = UUID.randomUUID();
  private static final UUID USER_ACCOUNT_ID = UUID.randomUUID();
  private static final UUID STORE_ID = UUID.randomUUID();

  @Deployment
  public static Archive<?> createTestArchive() {
    log.info("creating archive for test");
    WebArchive war =
        ShrinkWrap
            .create(WebArchive.class, "IntegrationModelTest.war")
            .addPackages(true, "com.brainmaster.apps", "com.brainmaster.util.formatter",
                "com.brainmaster.util.helper.uuid", "com.brainmaster.util.types",
                "org.picketlink.idm.model", "org.picketlink.idm.query",
                "org.picketlink.idm.IdentityManagementException")
            .addClasses(IntegrationModelRepositoryBean.class)
            .addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml")
            // .addAsManifestResource("test-ds.xml", "test-ds.xml")
            // .addAsResource("META-INF/jboss-deployment-structure.xml",
            // "META-INF/jboss-deployment-structure.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
            // .addAsWebInfResource("test-ds.xml")
            .addAsLibraries(
                Maven
                    .resolver()
                    .resolve("org.apache.commons:commons-lang3:3.1",
                        "commons-codec:commons-codec:20041127.091804").withTransitivity().asFile());
    war.writeTo(System.out, org.jboss.shrinkwrap.api.formatter.Formatters.VERBOSE);
    System.err.println();
    return war;
  }

  @Test(dependsOnMethods = "testIntegrationRepositoryBeanInjection")
  public void testCreatingAccount() {
    Account account = new Account(ACCOUNT_UUID, EMAIL_ID, "ucup", "sanusi");
    repositoryBean.save(account);
    Assert.assertNotNull(account.getAccountId(), "account is null");
    log.info(account.toString());
  }

  @Test(dependsOnMethods = {"testCreatingUserFromAccount"})
  public void testCreatingBrand() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Brand brand = repositoryBean.save(new Brand(user.getAccount(), BRAND_NAME, user, null));
    Assert.assertNotNull(brand.getKeys().getBrandName(), "brand name is null");
    Assert.assertEquals(
        repositoryBean.getAllBrandFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
            .size(), 1);
    log.info(brand.toString());
  }

  @Test(dependsOnMethods = {"testCreatingBrand"})
  public void testCreatingCategory() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Account account = user.getAccount();
    Assert.assertNotNull(account.getAccountId(), "account id is null");
    Category category = repositoryBean.save(new Category(account, CATEGORY_NAME, user, null));
    Assert.assertNotNull(category.getKeys().getCategoryName(), "category name is null");
    Assert.assertEquals(
        repositoryBean.getAllCategoryFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
            .size(), 1);
    log.info(category.toString());
  }

  @Test(dependsOnMethods = {"testCreatingCategory"})
  public void testCreatingCategoryChild() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Account account = user.getAccount();
    Assert.assertNotNull(account.getAccountId(), "account id is null");
    Category category = new Category(account, CATEGORY_NAME + "A", user, null);
    Category categoryChild =
        repositoryBean.save(new Category(account, CATEGORY_NAME + "A1", category, user, null));
    Assert.assertNotNull(category.getKeys().getCategoryName(), "parent category name is null");
    Assert.assertNotNull(categoryChild.getKeys().getCategoryName(), "parent category name is null");
    Assert.assertEquals(
        repositoryBean.getAllCategoryFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
            .size(), 3);
    log.info(category.toString());
    log.info(categoryChild.toString());

  }

  @Test(dependsOnMethods = {"testCreatingCategory"})
  public void testCreatingPackaging() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Account account = user.getAccount();
    Assert.assertNotNull(account.getAccountId(), "account id is null");
    PackagingUnit packagingUnit =
        repositoryBean.save(new PackagingUnit(new PackagingAccountKeys(account, PACKAGING_CODE),
            "Kilogram", user, null));
    Assert.assertNotNull(packagingUnit.getKeys().getPackagingId(), "packaging name is null");
    Assert.assertEquals(
        repositoryBean.getAllPackagingFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
            .size(), 1);
    log.info(packagingUnit.toString());
  }

  @Test(dependsOnMethods = {"testCreatingPackaging"})
  public void testCreatingProduct() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Account account = user.getAccount();
    Assert.assertNotNull(account.getAccountId(), "account id is null");
    Brand brand = repositoryBean.getBrandFromKey(new BrandAccountKeys(account, BRAND_NAME));
    Category category =
        repositoryBean.getCategoryFromKey(new CategoryAccountKeys(account, CATEGORY_NAME));
    PackagingUnit packaging =
        repositoryBean.getPackagingFromKey(new PackagingAccountKeys(account, PACKAGING_CODE));

    Product product1 =
        repositoryBean.save(new Product(account, PRODUCT_CODE1, "Barang Terlarang", "1001", brand,
            category, packaging, user, null));
    Assert.assertNotNull(product1.getProductCode(), "product code is null");
    log.info(product1.toString());

    Product product2 =
        repositoryBean.save(new Product(account, PRODUCT_CODE2, "Barang Terlarang 2", "1002",
            brand, category, packaging, user, null));
    Assert.assertNotNull(product2.getProductCode(), "product code is null");
    log.info(product2.toString());

    Assert.assertEquals(
        repositoryBean.getAllProductFromAccount(repositoryBean.getAccountFromKey(ACCOUNT_UUID))
            .size(), 2);
  }

  @Test(dependsOnMethods = {"testCreatingUserFromStore"})
  public void testCreatingProductForStore() {
    User user = repositoryBean.getUser(EMAIL_ID, false);
    Store store = repositoryBean.getStoreFromKey(STORE_ID, false, false);
    List<Product> products = new ArrayList<Product>();
    products.add(repositoryBean.getProductFromCode(store.getAccount(), PRODUCT_CODE1));
    products.add(repositoryBean.getProductFromCode(store.getAccount(), PRODUCT_CODE2));
    store = repositoryBean.createProductForStore(store, products, user);
    Assert.assertEquals(store.getProducts().size(), 2);
  }

  @Test(dependsOnMethods = {"testCreatingStore"})
  public void testCreatingRole() {
    Role anonymousRole = repositoryBean.save(new Role(ANONYMOUS_ROLE, "unknown user"));
    Assert.assertNotNull(repositoryBean.getRoleFromKey(anonymousRole.getRoleId()),
        "anonymous role is not save");
    Role userRole = repositoryBean.save(new Role(USER_ROLE, "defined user"));
    Assert.assertNotNull(repositoryBean.getRoleFromKey(userRole.getRoleId()),
        "user role is not save");
  }

  @Test(dependsOnMethods = {"testCreatingProduct"})
  public void testCreatingStore() {
    User user = repositoryBean.getUser(EMAIL_ACCOUNT_ID, true);
    Account account = user.getAccount();
    Assert.assertNotNull(account.getAccountId(), "account id is null");
    CompanyInformation companyInformation =
        new CompanyInformation("Jl. Perjuangan Tiada Akhir", "Dwiwarna, Karang anyar",
            "Jakarta Pusat", "11111", "Jakarta", "Jakarta Utara", "Support@me.com", "123456",
            "123456", "123456");
    BankInformation bankInformation =
        new BankInformation("Paijo", "1234567", "bank miun", "cabang satusatunya", "1234",
            "Jl. Karang Anyar A", "Jakarta Pusat", "haloo", "123456", "Jakarta Pusat",
            "DKI Jakarta");
    Store store =
        new Store(account, STORE_ID, A_STORE_NAME, StoreType.VENDOR, "Hello", null, user, null);
    StoreDetail storeDetail = new StoreDetail(store, companyInformation, bankInformation);
    store.setStoreDetail(storeDetail);
    Assert.assertNotNull(repositoryBean.save(store));
    Assert
        .assertEquals(repositoryBean.getAllStoreFromAccountId(account.getAccountUuid()).size(), 1);
    log.info(store.toString());
    log.info("#store id : {}", new Object[] {STORE_ID});
  }

  @Test(dependsOnMethods = {"testCreatingAccount"})
  public void testCreatingUserFromAccount() {
    Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
    account =
        repositoryBean.addUserFromAccount(ACCOUNT_UUID, new User(USER_ACCOUNT_ID, "ucup.sanusi",
            EMAIL_ACCOUNT_ID, ArrayUtils.toObject(DigestUtils.md5Hex("simplePassword").getBytes()),
            "ucup", "sanusi", account));
    Assert.assertEquals(account.getUsers().size(), 1);

  }

  @Test(dependsOnMethods = {"testCreatingAccount", "testCreatingRole"})
  public void testCreatingUserFromStore() {
    log.info("store id : {}", new Object[] {STORE_ID});
    Account account = repositoryBean.getAccountFromKey(ACCOUNT_UUID);
    User user =
        new User(USER_ID, "ucup.sanusis", EMAIL_ID, ArrayUtils.toObject(DigestUtils.md5Hex(
            "simplePassword").getBytes()), "ucup", "sanusis", account);
    account = repositoryBean.addUserFromAccount(ACCOUNT_UUID, user);
    Assert.assertEquals(account.getUsers().size(), 2);
    Store store = repositoryBean.addUserFromStore(STORE_ID, user);
    Assert.assertEquals(store.getUserStores().size(), 1);
  }

  @Test(dependsOnMethods = {"testCreatingRole", "testCreatingUserFromStore"})
  public void testCreatingUserRole() {
    User user = repositoryBean.getUser(EMAIL_ID, false);
    Role anonymousRole = repositoryBean.getRoleFromKey(ANONYMOUS_ROLE);
    Role userRole = repositoryBean.getRoleFromKey(USER_ROLE);
    List<Role> roles = new ArrayList<Role>();
    roles.add(userRole);
    roles.add(anonymousRole);
    user = repositoryBean.createUserWithRoles(user, roles);
    Assert.assertEquals(user.getUserRoles().size(), 2);
  }

  @Test
  public void testIntegrationRepositoryBeanInjection() {
    Assert.assertNotNull(repositoryBean, "ejb is null");
    log.info("successsfully injecting ejb");
  }
}
