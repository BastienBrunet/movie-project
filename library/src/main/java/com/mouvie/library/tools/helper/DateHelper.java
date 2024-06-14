package com.mouvie.library.tools.helper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateHelper {

    public static String formatDate(Date date){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);

        return df.format(date);
    }
}
