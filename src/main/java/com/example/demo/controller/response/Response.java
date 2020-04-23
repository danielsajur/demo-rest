package com.example.demo.controller.response;

import java.util.Date;
import java.util.List;

public class Response<R> {

	private _Error error;
	private List<R> data;
	private final Long timestamp;
	
	public Response() {
		this.timestamp = new Date().getTime();
	}
	
	public Response(_Error error) {
		this();
		this.setError(error);
	}

	public Response(List<R> data) {
		this();
		this.setData(data);
	}


	public Long getTimestamp() {
		return timestamp;
	}
	public List<R> getData() {
		return data;
	}
	protected void setData(List<R> data) {
		this.data = data;
	}
	public _Error getError() {
		return error;
	}
	protected void setError(_Error error) {
		this.error = error;
	}
	
	
}
