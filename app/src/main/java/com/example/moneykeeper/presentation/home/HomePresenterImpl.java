package com.example.moneykeeper.presentation.home;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoriesByNameUseCase;
import com.example.domain.interactor.transaction.DeleteTransactionsDataUseCase;
import com.example.domain.interactor.transaction.GetTransactionsDataUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Record;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.MathUtils;
import utils.MoneyKeeperUtils;
import utils.TimeUtils;

public class HomePresenterImpl implements HomeContract.Presenter {

    @Inject
    GetTransactionsDataUseCase getTransactionsDataUseCase;
    @Inject
    DeleteTransactionsDataUseCase deleteTransactionsDataUseCase;
    @Inject
    GetCategoriesByNameUseCase getCategoriesByNameUseCase;

    private HomeContract.View mView;

    @Inject
    HomePresenterImpl() {
    }

    @Override
    public void attachView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void getAllTransactionData() {
        getTransactionsDataUseCase.execute(new GetAllTransactionsObserver(), new EmptyParam());
    }

    @Override
    public void deleteAllTransactionData() {
        deleteTransactionsDataUseCase.execute(new DeleteTransactionsObserver(), new EmptyParam());
    }

    private List<Record> getSummaryRecordList(List<Transaction> transactionList) {
        long expenseSum;
        long incomeSum;
        List<Record> recordList = new ArrayList<>(2);
        //Get today record
        List<Transaction> todayTransactions = MoneyKeeperUtils.getTodayTransactionList(transactionList);
        expenseSum = MathUtils.getExpenseSum(todayTransactions);
        incomeSum = MathUtils.getIncomeSum(todayTransactions);
        recordList.add(new Record(incomeSum, expenseSum, TimeUtils.getCurrentDate(), TimeUtils.getCurrentMonth(), TimeUtils.getCurrentYear()));

        //Get Monthly record
        List<Transaction> currentMonthTransactions = MoneyKeeperUtils.getTransactionListFromSpecificMonth(transactionList, TimeUtils.getCurrentMonth());
        expenseSum = MathUtils.getExpenseSum(currentMonthTransactions);
        incomeSum = MathUtils.getIncomeSum(currentMonthTransactions);
        recordList.add(new Record(incomeSum, expenseSum, 0, TimeUtils.getCurrentMonth(), TimeUtils.getCurrentYear()));
        return recordList;
    }

    private class GetAllTransactionsObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            getCategoriesByNameUseCase.execute(new GetCategotyByNameObserver(), transactions);

        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e("onError", e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.d("onComplete", "Completed");
        }
    }


    private class DeleteTransactionsObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Log.e("TRANSACTION", "DELETE SUCCESSFUL");
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }
    }

    private class GetCategotyByNameObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            List<Record> recordList = getSummaryRecordList(transactions);
            mView.showSummaryList(recordList);
            mView.showTransactionList(transactions);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
