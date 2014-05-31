package com.brainmaster.apps.invoicing.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import com.brainmaster.apps.invoicing.core.model.Brand;
import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.id.BrandAccountKeys;

@Stateless
public class BrandServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;

  public void delete(Brand brand) throws Exception {
    log.debug("deleting brand : {}", brand);
    Brand savedBrand = getEntityManager().find(Brand.class, brand.getKeys());
    if (savedBrand != null) {
      getEntityManager().remove(savedBrand);
    } else {
      throw new EntityNotFoundException("can not delete unsaved brand : " + brand);
    }
  }

  public List<Brand> getAllBrandFromAccount(Account account) {
    return getAllBrandFromAccount(account, null, null);
  }

  @SuppressWarnings("unchecked")
  public List<Brand> getAllBrandFromAccount(Account account, Integer pageSize, Integer pageNumber) {
    log.debug("get all brand object from account : {}, pageSize : {}, pageNumber : {}",
        new Object[] {account, pageSize, pageNumber});
    return paginationQueryFor("brandFromAccount", pageSize, pageNumber).getResultList();
  }

  public Brand getBrandFromKey(BrandAccountKeys brandAccountKeys) {
    log.debug("get object for brand with keys : {}", brandAccountKeys);
    return getEntityManager().find(Brand.class, brandAccountKeys);
  }

  public Brand getReference(BrandAccountKeys brandAccountKeys) {
    log.debug("get reference for brand with keys : {}", brandAccountKeys);
    return getEntityManager().getReference(Brand.class, brandAccountKeys);
  }

  public Brand save(Brand brand) throws Exception {
    log.debug("saving brand : {}", brand);
    Brand savedBrand = getBrandFromKey(brand.getKeys());
    if (savedBrand != null) {
      BeanUtils.copyProperties(savedBrand, brand);
      brand = getEntityManager().merge(savedBrand);
    }
    getEntityManager().persist(brand);
    getEntityManager().flush();
    return brand;
  }

}
