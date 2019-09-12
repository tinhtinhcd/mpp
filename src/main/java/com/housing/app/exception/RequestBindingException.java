package com.housing.app.exception;

@ErrorCode(status = 400, code = ErrorEnum.BAD_REQUEST)
public class RequestBindingException extends BaseException {

  public RequestBindingException(String errorMessage) {
    super(errorMessage);
  }
}
