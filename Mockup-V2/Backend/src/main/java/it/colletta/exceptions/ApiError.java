package it.colletta.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError() {
    super();
  }

  /**
   * 
   * @param status
   * @param message
   * @param errors
   */
  public ApiError(final HttpStatus status, final String message, final List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  /**
   * 
   * @param status
   * @param message
   * @param error
   */
  public ApiError(final HttpStatus status, final String message, final String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }

  /**
   * 
   * @return
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * 
   * @param status
   */
  public void setStatus(final HttpStatus status) {
    this.status = status;
  }
  
  /**
   * 
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * 
   * @param message
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * 
   * @return
   */
  public List<String> getErrors() {
    return errors;
  }

  /**
   * 
   * @param errors
   */
  public void setErrors(final List<String> errors) {
    this.errors = errors;
  }

  /**
   * 
   * @param error
   */
  public void setError(final String error) {
    errors = Arrays.asList(error);
  }
}
