package com.brainmaster.apps.invoicing.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractServiceBean {

  @Inject
  private EntityManager entityManager;

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public Query paginationQueryFor(String namedQuery, Integer pageSize, Integer pageNumber) {
    Query query = getEntityManager().createNamedQuery(namedQuery);
    if ((pageSize != null && !(pageSize.intValue() < 0))
        && (pageNumber != null && !(pageNumber.intValue() < 0))) {
      query.setFirstResult(pageNumber * pageSize);
      query.setMaxResults(pageSize);
    }
    return query;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
