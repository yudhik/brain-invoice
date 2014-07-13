package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Category;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.id.CategoryAccountKeys;

@Stateless
public class CategoryServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public void delete(Category category) throws Exception {
    log.debug("deleting category : {}", category);
    Category savedCategory = getEntityManager().find(Category.class, category.getKeys());
    if (savedCategory != null) {
      getEntityManager().remove(savedCategory);
    } else {
      throw new EntityNotFoundException("can not delete unsaved brand : " + category);
    }
    getEntityManager().flush();
  }

  public List<Category> getAllCategoryFromAccount(Account account) {
    log.debug("get all category from account : {}", account);
    return getAllCategoryFromAccount(account, null, null);
  }

  @SuppressWarnings("unchecked")
  public List<Category> getAllCategoryFromAccount(Account account, Integer pageSize,
      Integer pageNumber) {
    log.debug("get all category object from account : {}, pageSize : {}, pageNumber : {}",
        new Object[] {account, pageSize, pageNumber});
    return paginationQueryFor("categoryFromAccount", pageSize, pageNumber).getResultList();
  }

  public Category getCategoryFromKey(CategoryAccountKeys categoryAccountKeys) {
    log.debug("get category object from key : {}", categoryAccountKeys);
    return getEntityManager().find(Category.class, categoryAccountKeys);
  }

  public Category getReference(CategoryAccountKeys categoryAccountKeys) {
    log.debug("get category reference from key : {}", categoryAccountKeys);
    return getEntityManager().getReference(Category.class, categoryAccountKeys);
  }

  public Category save(Category category) throws Exception {
    log.debug("save category : {}", category);
    Category savedCategory = getCategoryFromKey(category.getKeys());
    if (savedCategory != null) {
      BeanUtils.copyProperties(savedCategory, category);
      category = getEntityManager().merge(savedCategory);
    } else {
      getEntityManager().persist(category);
    }
    getEntityManager().flush();
    return category;
  }
}
