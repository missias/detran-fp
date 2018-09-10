package br.gov.al.detran.detranfp;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "message" })
public class Response {

	private String code;
	private String message;

	private Response() {
		super();
		this.code = "";
		this.message = "";
	}

	public Response(HttpStatus status) {
		this();
		this.code = Integer.toString(status.value());
	}

	public Response(HttpStatus status, String message) {
		this();
		this.message = message;
	    this.code = Integer.toString(status.value());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + "]";
	}


}
