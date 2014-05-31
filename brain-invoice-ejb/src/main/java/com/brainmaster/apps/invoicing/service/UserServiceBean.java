package com.brainmaster.apps.invoicing.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

@Stateless
public class UserServiceBean extends AbstractServiceBean {

  @Inject
  private Logger log;
}
