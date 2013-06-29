package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_store")
public class ProductStore implements Serializable {

    private static final long serialVersionUID = -7826385577001790718L;

}
