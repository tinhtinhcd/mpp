package com.housing.app.util;

import com.housing.app.common.AppConstant;
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

    public static void validateImage(String imageType) {
        boolean support = false;
        for (String type : AppConstant.SUPPORT_IMAGE_EXTENSIONS) {
            if (type.equalsIgnoreCase(imageType)) {
                support = true;
                break;
            }
        }
        if (!support)
            throw new RequestBindingException("Image type was not support, please upload file type (gif, jpg, png)");
    }
}
