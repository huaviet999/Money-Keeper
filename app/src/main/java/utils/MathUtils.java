package utils;

import android.util.Log;

import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.Constants;

import java.text.NumberFormat;
import java.util.List;
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

    public static String getFormatNumberFromLong(long value) {
        String formattedValue = String.format("%,d", value);
        return String.valueOf(formattedValue + " " + VN_CURRENCY);
    }

    public static String getFormatNumberFromLongWithoutCurrency(long value) {
        String formattedValue = String.format("%,d", value);
        return String.valueOf(formattedValue);
    }

    public static String getNumberWithVietNamCurrency(String value) {
        return String.valueOf(getFormatNumberWithCommas(value) + " " + VN_CURRENCY);
    }

    public static long getExpenseSum(List<Transaction> transactions) {
        long expenseSum = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(Constants.KEY_EXPENSE)) {
                expenseSum += transaction.getAmount();
            }
        }
        return expenseSum;
    }

    public static long getIncomeSum(List<Transaction> transactions) {
        long incomeSum = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(Constants.KEY_INCOME)) {
                incomeSum += transaction.getAmount();
            }
        }
        return incomeSum;
    }

    public static long getTransactionSum(List<Transaction> transactionList) {
        long sum = 0;
        for (Transaction transaction : transactionList) {
                sum += transaction.getAmount();
        }
        return sum;
    }

//    public static float getPercentByCategoryType(List<Transaction> transactionList, String categoryType, String transactionType) {
//        float percent;
//        long total = 0;
//        long categorySum = getSumByCategoryType(transactionList, categoryType);
//        switch (transactionType) {
//            case Constants.KEY_INCOME:
//                total = getIncomeSum(transactionList);
//                break;
//            case Constants.KEY_EXPENSE:
//                total = getExpenseSum(transactionList);
//                break;
//        }
//        percent = ((float) categorySum / total)*100;
//        Log.e("SUM",String.valueOf(categorySum));
//        Log.e("SUM",String.valueOf(total));
//        Log.e("SUM",String.valueOf(percent));
//        return percent;
//    }
}
