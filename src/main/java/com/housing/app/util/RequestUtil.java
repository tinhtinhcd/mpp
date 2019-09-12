package com.housing.app.util;

import com.housing.app.exception.RequestBindingException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class RequestUtil {

  public static void validateRequest(BindingResult result) {
    if (result.hasErrors()) {
      StringBuffer errorMessage = new StringBuffer();
      for (ObjectError error : result.getAllErrors()) {
        errorMessage.append(error.getDefaultMessage() + ", ");
      }
      throw new RequestBindingException(errorMessage.toString());
    }
  }
}
