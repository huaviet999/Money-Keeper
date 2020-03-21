package utils;

import com.example.domain.model.ExpenseType;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 3/20/2020
 */
public class MoneyKeeperUtils {

    public static List<Transaction> getTransactionListFromSpecificMonth(List<Transaction> transactionList, int month) {
        List<Transaction> sortedTransactionList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            int transactionMonth = TimeUtils.getMonthFromMilliseconds(transaction.getDate());
            if (transactionMonth == month) {
                sortedTransactionList.add(transaction);
            }
        }
        return sortedTransactionList;
    }

    public static List<Transaction> getTodayTransactionList(List<Transaction> transactionList) {
        int currentDate = TimeUtils.getCurrentDate();
        int currentMonth = TimeUtils.getCurrentMonth();
        int currentYear = TimeUtils.getCurrentYear();
        List<Transaction> sortedTransactionList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            int transactionMonth = TimeUtils.getMonthFromMilliseconds(transaction.getDate());
            int transactionDate = TimeUtils.getDayFromMilliseconds(transaction.getDate());
            int transactionYear = TimeUtils.getYearFromMilliseconds(transaction.getDate());

            if (transactionDate == currentDate && transactionMonth == currentMonth && transactionYear == currentYear) {
                sortedTransactionList.add(transaction);
            }
        }
        return sortedTransactionList;
    }

}
