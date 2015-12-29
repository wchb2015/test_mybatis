package com.lvmama.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangchongbei on 15-12-29.
 */
public class DateUtils {

    public static Date getDateAfter2359Date(Date date, int duration) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, duration);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

}
