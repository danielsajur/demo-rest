package com.example.demo.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder<R> {

	private BuilderError builderError;

	private BuilderSuccess builderSuccess;

	public BuilderSuccess withData(List<R> data) {
		if (builderSuccess == null) {
			builderSuccess = new BuilderSuccess(data);
		}

		return builderSuccess;
	}
	
	public BuilderSuccess withMessage(String message) {
		if (builderSuccess == null) {
			builderSuccess = new BuilderSuccess(message);
		}

		return builderSuccess;
	}

	public BuilderError withError(_Error error) {
		if (builderError == null) {
			builderError = new BuilderError(error);
		}
		return builderError;
	}

	public ResponseBuilder() {
		
	}

	public abstract class Builder {

		protected Response<R> response;

		public Builder withHttp(HttpStatus http) {
			response.setHttp(http);
			return this;
		}

		public ResponseEntity<Response<R>> build() {
			return ResponseEntity.status(response.getStatus()).body(response);
		}
	}

	public class BuilderError extends Builder {

		public BuilderError(_Error error) {
			response = new Response<R>(error);
		}

	}

	public class BuilderSuccess extends Builder {

		public BuilderSuccess(List<R> data) {
			response = new Response<R>(data);
		}
		
		public BuilderSuccess(String message) {			
			
			if(response == null) {
				response = new Response<R>(message);
				return;
			}
			
			response.setMessage(message);
		}
		public BuilderSuccess withMessage(String message) {
			
			if(response == null) {
				throw new IllegalArgumentException("response == null");
			}
			
			response.setMessage(message);
			return this;
		}
	}

}
