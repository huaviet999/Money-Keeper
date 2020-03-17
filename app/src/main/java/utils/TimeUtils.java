package utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Viet Hua on 3/17/2020
 */
public class TimeUtils {
    public static final String VN_DATE_FORMAT = "dd/MM/YYYY";
    public static final String DAY_OF_THE_WEEK_FORMAT = "EEEE";

    //Return Monday , Tuesday,...
    public static String getCurrentDayOfTheWeek(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_OF_THE_WEEK_FORMAT);
        String dayOfTheWeek = simpleDateFormat.format(date);
        return dayOfTheWeek;
    }

    //Example : 17/07/1999
    public static String getCurrentDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        String formattedDate = simpleDateFormat.format(date);
        return formattedDate;
    }

    //Example : Tuesday, 17/07/1999
    public static String getToday(){
        String currentDayOfTheWeek = getCurrentDayOfTheWeek();
        String currentDate = getCurrentDate();
        String today = String.valueOf(currentDayOfTheWeek + ", " + currentDate);
        return today;
    }

    public static String getDateFormat(int year,int month,int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Date date = new Date(year, month, dayOfMonth-1);

        SimpleDateFormat dateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        SimpleDateFormat dayOfTheWeekformat = new SimpleDateFormat(DAY_OF_THE_WEEK_FORMAT);
        String formattedDate = dateFormat.format(calendar.getTime());
        String dayOfWeek = dayOfTheWeekformat.format(date);
        return String.valueOf(dayOfWeek + ", " + formattedDate);
    }

}
