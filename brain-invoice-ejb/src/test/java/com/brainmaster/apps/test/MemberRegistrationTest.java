package com.brainmaster.apps.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import com.brainmaster.apps.invoicing.model.Member;
import com.brainmaster.apps.invoicing.service.MemberRegistration;
import com.brainmaster.apps.util.Resources;

public class MemberRegistrationTest extends Arquillian {
  @Deployment
  public static Archive<?> createTestArchive() {
    return ShrinkWrap.create(WebArchive.class, "test.war")
        .addClasses(Member.class, MemberRegistration.class, Resources.class)
        .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        // Deploy our test datasource
        .addAsWebInfResource("test-ds.xml", "test-ds.xml");
  }

  @Inject
  MemberRegistration memberRegistration;

  @Inject
  Logger log;

  @Test
  public void testRegister() throws Exception {
    Member newMember = new Member();
    newMember.setName("Jane Doe");
    newMember.setEmail("jane@mailinator.com");
    newMember.setPhoneNumber("2125551234");
    memberRegistration.register(newMember);
    assertNotNull(newMember.getId());
    log.info(newMember.getName() + " was persisted with id " + newMember.getId());
  }

}
