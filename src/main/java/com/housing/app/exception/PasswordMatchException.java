package com.housing.app.exception;

@ErrorCode(status = 401, code = ErrorEnum.PASSWORD_NOT_MATCH)
public class PasswordMatchException  extends BaseException {
  public PasswordMatchException() {
    super(ErrorEnum.PASSWORD_NOT_MATCH.getMessage());
  }
}
