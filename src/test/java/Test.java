import com.lvmama.utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangchongbei on 15-12-29.
 */
public class Test {

    @org.junit.Test
    public void testDate() {
        DateUtils.getDateAfter2359Date(new Date(), 1);
        System.out.println("haha");
    }

    @org.junit.Test
    public void getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.get(Calendar.DATE);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse("2015-06-29 23:20:00");
            Date d2 = df.parse("2015-06-30 08:15:00");
            long diff = d2.getTime() - d1.getTime();
            long minute = diff / (1000 * 60);
            System.out.println(minute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date getDateAfterDays(Date date, int duration) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, duration);
        return cal.getTime();
    }

    public static Date getAfterHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

}
