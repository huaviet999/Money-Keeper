package com.example.moneykeeper.presentation.chart;

import android.util.Log;

import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.interactor.transaction.GetTransactionsDataUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.MathUtils;

public class ChartPresenterImpl implements ChartContract.Presenter {
    @Inject
    GetTransactionByTypeUseCase getTransactionByTypeUseCase;

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
        getTransactionByTypeUseCase.execute(new GetTrasacntionsByTypeObserver(), transactionType);
    }

    private class GetTrasacntionsByTypeObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            Set<Category> categories = new HashSet<>();
            for (Transaction transaction : transactions) {
                Log.e("TRANSACTION", transaction.getCategory().getName());
                categories.add(transaction.getCategory());
            }
            for (Category category : categories) {
                Log.e("CATEGORY", category.getName());
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
