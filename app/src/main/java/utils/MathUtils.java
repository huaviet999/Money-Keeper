package utils;

/**
 * Created by Viet Hua on 3/17/2020
 */
public class MathUtils {
    public static final String VN_CURRENCY = "VNĐ";

    public static String getFormatNumberWithCommas(String value) {
        long parsedValue = Long.parseLong(value);
        return String.format("%,d", parsedValue);
    }

    public static String getNumberWithVietNamCurrency(String value) {
        return String.valueOf(getFormatNumberWithCommas(value) + " " + VN_CURRENCY);
    }
}
