package com.example.moneykeeper.presentation.chart;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoriesByNameUseCase;
import com.example.domain.interactor.transaction.GetSumAndPercentUseCase;
import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.MathUtils;
import utils.MoneyKeeperUtils;

public class ChartPresenterImpl implements ChartContract.Presenter {
    @Inject
    GetTransactionByTypeUseCase getTransactionByTypeUseCase;

    @Inject
    GetSumAndPercentUseCase getSumAndPercentUseCase;
    @Inject
    GetCategoriesByNameUseCase getCategoriesByNameUseCase;

    ChartContract.View mView;

    @Inject
    public ChartPresenterImpl() {

    }

    @Override
    public void attachView(ChartContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getTransactionListByType(String transactionType) {
        getTransactionByTypeUseCase.execute(new GetTransactionsByTypeObserver(), transactionType);
    }

    private class GetTransactionsByTypeObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            getCategoriesByNameUseCase.execute(new GetCategotyByNameObserver(),transactions);
            long total = MathUtils.getTransactionSum(transactions);
            mView.showTotal(total);

        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private class GetTransactionListByCategoryObserver extends DisposableMaybeObserver<List<Percent>> {
        @Override
        public void onSuccess(@NonNull List<Percent> percents) {
            mView.showPercentList(percents);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
    private class GetCategotyByNameObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {

            List<Percent> percents = new ArrayList<>();
            List<Category> categoryList = MoneyKeeperUtils.getUniqueCategoryList(transactions);
            for (Category category : categoryList) {

                percents.add(new Percent(category));
            }


            getSumAndPercentUseCase.execute(new GetTransactionListByCategoryObserver(), percents);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
