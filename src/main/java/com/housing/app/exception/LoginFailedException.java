package com.housing.app.exception;

@ErrorCode(status = 401, code = ErrorEnum.LOGIN_FAILED)
public class LoginFailedException extends BaseException {

  public LoginFailedException() {
    super(ErrorEnum.LOGIN_FAILED.getMessage());
  }
}
