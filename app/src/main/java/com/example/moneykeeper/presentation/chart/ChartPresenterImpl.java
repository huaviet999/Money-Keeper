package com.example.moneykeeper.presentation.chart;

import android.util.Log;

import com.example.domain.interactor.transaction.GetTransactionsDataUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.MathUtils;

public class ChartPresenterImpl implements ChartContract.Presenter {
    @Inject
    GetTransactionsDataUseCase getTransactionsDataUseCase;

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
    public void getAllTransactionList() {
        getTransactionsDataUseCase.execute(new GetAllTransactionListObserver(), new EmptyParam());
    }

    private class GetAllTransactionListObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
//            float test = MathUtils.getPercentByCategoryType(transactions,"Travel",Constants.KEY_EXPENSE);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
