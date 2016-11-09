package kr.pe.jady.store.batch.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jhlee7854 on 2016. 11. 4..
 */
public class DateUtil {
    public static String calculateDateString(String basicDate, int amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(basicDate));
        calendar.add(Calendar.DATE, amount);
        return sdf.format(calendar.getTime());
    }

    public static String getYesterdayDateString() throws ParseException {
        return calculateDateString(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), -1);
    }

    public static Calendar setStartTime(Calendar calendar) {
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static Calendar setEndTime(Calendar calendar) {
        calendar.set(Calendar.AM_PM, 1);
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    public static Calendar convertDateStringToCalendar(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (inputDate != null && !inputDate.isEmpty()) {
            calendar.setTime(sdf.parse(inputDate));
        } else {
            calendar.setTime(new Date());
        }
        return calendar;
    }
}
