package com.brainmaster.apps.invoicing.rest;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.slf4j.Logger;

@Path("/accounts")
@RequestScoped
public class AccountResources {

  @Inject
  private Logger log;

}
