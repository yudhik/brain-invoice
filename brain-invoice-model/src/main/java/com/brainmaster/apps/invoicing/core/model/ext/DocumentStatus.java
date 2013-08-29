package com.brainmaster.apps.invoicing.core.model.ext;

public enum DocumentStatus {
    CREATED("CREATED"), DRAFT("DRAFT"), SENT("SENT"), DELETED("DELETED"), ARCHIVED("ARCHIVED");

    private String shortCall;

    private DocumentStatus(final String shortCall) {
	this.shortCall = shortCall;
    }

    @Override
    public String toString() {
	return shortCall;
    }
}
