package com.housing.app.exception;

@ErrorCode(status = 400, code = ErrorEnum.ENTITY_NOT_FOUND)
public class EntityNotFoundException extends BaseException {

  public EntityNotFoundException() {
    super(ErrorEnum.ENTITY_NOT_FOUND.getMessage());
  }

}
