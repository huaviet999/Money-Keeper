package utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Viet Hua on 3/17/2020
 */
public class MathUtils {
    public static final String VN_CURRENCY = "VNĐ";

    public static String getFormatNumberWithCommas(String value) {
        long parsedValue = Long.parseLong(value);
        return String.format("%,d", parsedValue);
    }

    public static long convertNumberFromStringToLong(String value) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.UK);
        try {
            Number number = numberFormat.parse(value);
            return number.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static String getFormatNumberFromLong(long value){
        String formattedValue = String.format("%,d",value);
        return String.valueOf(formattedValue + " " + VN_CURRENCY);
    }

    public static String getNumberWithVietNamCurrency(String value) {
        return String.valueOf(getFormatNumberWithCommas(value) + " " + VN_CURRENCY);
    }
}
