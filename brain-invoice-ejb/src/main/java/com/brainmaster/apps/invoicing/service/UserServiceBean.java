package com.brainmaster.apps.invoicing.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.UserStore;
import com.brainmaster.apps.invoicing.core.model.credential.Role;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.credential.UserRole;
import com.brainmaster.apps.invoicing.core.model.id.UserRoleKeys;

@Stateless
public class UserServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  @EJB
  private StoreServiceBean storeServiceBean;

  public Store addUserFromStore(UUID storeId, User user) throws Exception {
    log.debug("add user from store : {}, user : {}", new Object[] {storeId, user});
    Store store = storeServiceBean.getStoreFromKey(storeId);
    store.getUserStores().add(new UserStore(user, store));
    store = storeServiceBean.save(store);
    Hibernate.initialize(store.getUserStores());
    return store;
  }

  public User createUserWithRoles(User user, List<Role> roles) throws Exception {
    log.debug("create user with roles, user : {}, roles : {}", new Object[] {user, roles});
    for (Role role : roles) {
      user = getUser(user.getEmailAddress(), false);
      user.getUserRoles().add(new UserRole(new UserRoleKeys(user, role)));
    }
    user = save(user);
    Hibernate.initialize(user.getUserRoles());
    return user;
  }

  public User getUser(String email, boolean fetchAccount) {
    log.debug("get user from email : {}, fetch Account : {}", new Object[] {email, fetchAccount});
    User user =
        getEntityManager().createNamedQuery("user-with-email", User.class)
            .setParameter("email", email).getSingleResult();
    if (user != null && fetchAccount) {
      Hibernate.initialize(user.getAccount());
    }
    return user;
  }

  public User save(User user) throws Exception {
    log.debug("save user : {}", user);
    User savedUser = getUser(user.getEmailAddress(), false);
    if (savedUser != null) {
      BeanUtils.copyProperties(savedUser, user);
      getEntityManager().merge(savedUser);
    } else {
      getEntityManager().persist(user);
    }
    getEntityManager().flush();
    return user;
  }
}
