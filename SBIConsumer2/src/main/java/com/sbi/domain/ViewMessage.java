package com.sbi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewMessage<E> {
	
	@JsonProperty
	private boolean containsError;
	@JsonProperty
	private String message;
	@JsonProperty
	private E data;

	public boolean isContainsError() {
		return containsError;
	}

	public void setContainsError(boolean containsError) {
		this.containsError = containsError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}
