package com.housing.app.util;


import com.housing.app.common.AppConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateUtil {
  private static DateFormat dateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT);

  public static Date convertStringToDate(String dateStr){
    try {
      return dateFormat.parse(dateStr);
    }catch (Exception e) {
      return null;
    }
  }
  public static String convertDateToString(Date date) {
    return dateFormat.format(date);
  }

}
