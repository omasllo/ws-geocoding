package o.masllo.wsgeocoding.model.dto;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private long timestamp;
	private String message;

	public ApiError(HttpStatus status, Throwable ex) {
		timestamp = System.currentTimeMillis();
		this.status = status;
		this.message = ex.getMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
