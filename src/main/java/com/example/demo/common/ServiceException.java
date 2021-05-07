package com.example.demo.common;

public class ServiceException extends RuntimeException {
  private Object result;

  public ServiceException() {
    super();
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  private static final long serialVersionUID = -1;

  private String obj;

  private String[] placeholders;

  public ServiceException(String value) {
    super();
    this.obj = value;
  }

  public ServiceException(String value, Object result) {
    super();
    this.obj = value;
    this.result = result;
  }

  public ServiceException(String value, String[] placeholder) {
    this.placeholders = placeholder;
    this.obj = value;
  }

  public ServiceException(String value, Object result, String[] placeholder) {
    this.result = result;
    this.placeholders = placeholder;
    this.obj = value;
  }

  public void setValue(String value) {
    this.obj = value;
  }

  public String[] getPlaceholders() {
    return placeholders;
  }

  public void setPlaceholders(String[] placeholders) {
    this.placeholders = placeholders;
  }

  @SuppressWarnings("unchecked")
  public String getValue() {
    return this.obj;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }
}
