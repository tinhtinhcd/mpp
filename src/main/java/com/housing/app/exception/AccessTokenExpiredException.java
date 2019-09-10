package com.housing.app.exception;

@ErrorCode(status = 401, code = ErrorEnum.ACCESS_TOKEN_EXPIRED)
public class AccessTokenExpiredException extends BaseException {

  public AccessTokenExpiredException() {
    super(ErrorEnum.ACCESS_TOKEN_EXPIRED.getMessage());
  }
}
