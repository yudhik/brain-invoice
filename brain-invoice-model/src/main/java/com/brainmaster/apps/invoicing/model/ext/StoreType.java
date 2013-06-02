package com.brainmaster.apps.invoicing.model.ext;

public enum StoreType {
    CUSTOMER("customer"), VENDOR("vendor"), BRANCH("branch");

    private String storeTypeName;

    private StoreType(String storeTypeName) {
	this.storeTypeName = storeTypeName;
    }

    @Override
    public String toString() {
	return storeTypeName;
    }
}
