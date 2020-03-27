package com.example.moneykeeper.presentation.chart;

import android.util.Log;

import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.interactor.transaction.GetTransactionListByCategoryUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;

public class ChartPresenterImpl implements ChartContract.Presenter {
    @Inject
    GetTransactionByTypeUseCase getTransactionByTypeUseCase;

    @Inject
    GetTransactionListByCategoryUseCase getTransactionListByCategoryUseCase;

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
            Set<Category> categories = new HashSet<>();
            for (Transaction transaction : transactions) {
                categories.add(transaction.getCategory());
            }

            for(Category category : categories){
                getTransactionListByCategoryUseCase.execute(new GetTransactionListByCategoryObserver(),category.getName());
            }


        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
    private class GetTransactionListByCategoryObserver extends DisposableMaybeObserver<List<Transaction>>{
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            for (Transaction transaction : transactions) {
                Log.d("TRANSACTION",transaction.getCategory().getName());
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
