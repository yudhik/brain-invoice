package com.brainmaster.apps.invoicing.model.ext;

public enum DocumentType {
	ORDER("PO"), DELIVERY_ORDER("DO"), RECIEVING_ADVISE("RA"), INVOICE("INV");
	
	private String shortCall;
	
	private DocumentType(final String shortCall) {
		this.shortCall = shortCall;
	}
	
	@Override
	public String toString() {
		return shortCall;
	}
	
	
}
