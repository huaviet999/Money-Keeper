package utils;

import android.util.Log;

import com.example.domain.model.Category;
import com.example.domain.model.ExpenseType;
import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static void getTransactionListByCategory(List<Transaction> transactions, Category category) {
        List<Transaction> sortedList = new ArrayList<>();
        Map<Category, List<Transaction>> map = new HashMap<Category, List<Transaction>>();
        for (Transaction transaction : transactions) {
            List<Transaction> list = map.get(transaction.getCategory());
            if (list == null) {
                list = new ArrayList<Transaction>();
                map.put(transaction.getCategory(), list);
            }
            list.add(transaction);
        }
        Log.d("MAPTEST", map.toString());

    }

    public static List<Category> getUniqueCategoryList(List<Transaction> transactions) {
        Set<Category> categories = new HashSet<>();
        for (Transaction transaction : transactions) {
            categories.add(transaction.getCategory());
        }
        List<Category> categoryList = new ArrayList<>(categories);
        return categoryList;
    }

}
