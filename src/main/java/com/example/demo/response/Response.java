package com.example.demo.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class Response<R> {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private _Error error;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<R> data;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int status;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String phrase;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final Long timestamp;

	public Response() {
		this.timestamp = new Date().getTime();
		status = HttpStatus.OK.value();
		phrase = HttpStatus.OK.getReasonPhrase();
	}
	
	public Response(String message) {
		this();
		this.setMessage(message);
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

	private void setData(List<R> data) {
		if (data == null) {
			throw new IllegalArgumentException("data == null");
		}
		this.data = data;
	}

	public _Error getError() {
		return error;
	}

	private void setError(_Error error) {
		if (error == null) {
			throw new IllegalArgumentException("error == null");
		}
		this.error = error;
	}

	public void setHttp(HttpStatus http) {
		if (http == null) {
			throw new IllegalArgumentException("http == null");
		}
		this.status = http.value();
		this.phrase = http.getReasonPhrase();
	}

	public int getStatus() {
		return status;
	}

	public String getPhrase() {
		return phrase;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
