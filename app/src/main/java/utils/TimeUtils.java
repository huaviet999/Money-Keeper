package utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Viet Hua on 3/17/2020
 */
public class TimeUtils {
    public static final String VN_DATE_SHORT_FORMAT = "dd/MM/YYYY";
    public static final String DAY_OF_THE_WEEK_FORMAT = "EEEE";
    public static final String VN_DATE_FORMAT = "EEEE dd/MM/YYYY";

//    public static String getCurrentDate() {
//        Date date = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
//        String formattedDate = simpleDateFormat.format(date);
//        return formattedDate;
//    }

    public static long getCurrentDateInMilliseconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static int getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        return day;
    }
    public static int getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        int day = calendar.get(Calendar.DATE);
        return day;
    }
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static String getDateFormat(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat(VN_DATE_FORMAT);
        String formattedDate = dateFormat.format(calendar.getTime());
        return formattedDate;
    }

    public static long getDateFormatInMilliseconds(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTimeInMillis();
    }


    public static String convertMillisecondsToDateFormat(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(VN_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String convertMillisecondsToShortDateFormat(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(VN_DATE_SHORT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
    public static Date convertMillisecondsToDate(long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return calendar.getTime();
    }

    public static int getMonthFromMilliseconds(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        int month = calendar.get(Calendar.MONTH) + 1; //ZERO-BASED
        return month;
    }

    public static int getDayFromMilliseconds(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        int day = calendar.get(Calendar.DATE);
        return day;
    }

    public static int getYearFromMilliseconds(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
}
