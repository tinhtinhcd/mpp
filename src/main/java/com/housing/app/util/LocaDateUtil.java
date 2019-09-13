package com.housing.app.util;


import com.housing.app.common.AppConstant;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocaDateUtil {
  private static DateFormat dateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT);

  public static Date convertStringToDate(String dateStr){
    try {
      return dateFormat.parse(dateStr);
    }catch (Exception e) {
      return null;
    }
  }

  public static Timestamp getCurrentTime(){
    Date now = new Date();
    return new Timestamp(now.getTime());
  }

  public static String convertDateToString(Date date) {
    return dateFormat.format(date);
  }

  public static int getCurrentYear() {
    return Calendar.getInstance().get(Calendar.YEAR);
  }
}