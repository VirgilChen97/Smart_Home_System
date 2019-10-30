package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description : The class used to process all the work related to date.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class DateUtil {

    /**
     * This method is used to obtain the "WEEK_OF_YEAR" and "MONTH" of the date you input
     * @param datestr the string of the date you want to parse
     * @return an array with the "WEEK_OF_YEAR" and "MONTH" of the date
     * @throws ParseException
     */
    //according to the input date, return a 2-d array with the order of week and month in a year.
	public static int[] getWeekMonth(String datestr) throws ParseException {
		
		int lastWeekMonth[] = new int[2];
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date;
		date = sdf.parse(datestr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY); 
        lastWeekMonth[0] = calendar.get(Calendar.WEEK_OF_YEAR);
        lastWeekMonth[1] = (calendar.get(Calendar.MONTH)) + 1;

        return lastWeekMonth;
	}
}
