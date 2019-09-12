package com.housing.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleExceptions(Exception ex) {

    ErrorCode errorCode = ex.getClass().getAnnotation(ErrorCode.class);
    if (errorCode != null) {
      ErrorEnum code = errorCode.code();
      return ResponseEntity.status(errorCode.status())
          .body(new ErrorDto(code.getCode(), code.getMessage() + ": " + ex.getMessage()));
    }
    logger.error("Internal Server Error: " + ex.getMessage(), ex);
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto(ErrorEnum.INTERNAL_SERVER_ERROR.getCode(), ErrorEnum.INTERNAL_SERVER_ERROR.getMessage()));
  }

}