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
}
