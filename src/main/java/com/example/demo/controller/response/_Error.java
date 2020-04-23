package com.example.demo.controller.response;

public class _Error {

	private final Long code;
	
	private final String description;

	public _Error(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Long getCode() {
		return code;
	}
	
	
}
