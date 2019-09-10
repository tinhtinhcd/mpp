package com.housing.app.exception;

@ErrorCode(status = 401, code = ErrorEnum.USER_NOT_EXISTED)
public class UserNotExistedException extends BaseException {
  public UserNotExistedException() {
    super(ErrorEnum.USER_NOT_EXISTED.getMessage());
  }
}
