package it.colletta.exceptions;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError() {
    super();
  }

  /**
   * Class constructor.
   */
  public ApiError(final HttpStatus status, final String message, final List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  /**
   * Class constructor.
   */
  public ApiError(final HttpStatus status, final String message, final String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }

  /**
   * get the status.
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * set the status.
   */
  public void setStatus(final HttpStatus status) {
    this.status = status;
  }

  /**
   * get String message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * set message.
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * get errors.
   */
  public List<String> getErrors() {
    return errors;
  }

  /**
   * set errors.
   */
  public void setErrors(final List<String> errors) {
    this.errors = errors;
  }

  /**
   * set error.
   */
  public void setError(final String error) {
    errors = Arrays.asList(error);
  }
}
