package com.housing.app.exception;

@ErrorCode(status = 400, code = ErrorEnum.EXISTED_USER)
public class UserExistedException extends BaseException {

  public UserExistedException() {
    super(ErrorEnum.EXISTED_USER.getMessage());
  }
}
