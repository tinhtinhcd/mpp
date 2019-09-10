package com.housing.app.util;

import com.housing.app.exception.RequestBindingException;
import org.springframework.validation.BindingResult;

public class RequestUtil {

  public static void validateRequest(BindingResult result) {
    if (result.hasErrors()) {
      throw new RequestBindingException();
    }
  }
}
