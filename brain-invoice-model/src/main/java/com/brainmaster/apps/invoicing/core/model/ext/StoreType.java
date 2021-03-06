package com.brainmaster.apps.invoicing.core.model.ext;

public enum StoreType {
  CUSTOMER("customer"), VENDOR("vendor");

  private String storeTypeName;

  private StoreType(String storeTypeName) {
    this.storeTypeName = storeTypeName;
  }

  @Override
  public String toString() {
    return storeTypeName;
  }
}
