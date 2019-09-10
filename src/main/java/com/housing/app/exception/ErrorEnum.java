package com.housing.app.exception;

public enum ErrorEnum {

  INTERNAL_SERVER_ERROR("LC00", "Internal Server"),
  LOGIN_FAILED("LC001", "Invalid user name or password"),
  EXISTED_USER("LC002", "User existed in system"),
  ENTITY_NOT_FOUND("LC003", "Entity not found"),
  BAD_REQUEST("LC004", "Bad request"),
  ACCESS_TOKEN_EXPIRED("LC005", "Access token is expired"),
  PASSWORD_NOT_MATCH("LC006", "Password not match"),
  USER_NOT_EXISTED("LC007", "User does not existed in system");

  private String code;
  private String message;

  ErrorEnum(final String code, final String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
