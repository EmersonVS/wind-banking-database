package com.wind.banking.app.models.helpers;

public class TransferError extends Exception {

	private static final long serialVersionUID = 1L;

	public TransferError(String errorMessage) {
		super(errorMessage);
	}
}
