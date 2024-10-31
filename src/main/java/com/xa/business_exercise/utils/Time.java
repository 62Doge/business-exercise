package com.xa.business_exercise.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

    public static String getYearMonthTime(){
        DateFormat df = new SimpleDateFormat("yyMM");
        String formattedDate = df.format(Calendar.getInstance().getTime());

        return formattedDate;
    }
}
