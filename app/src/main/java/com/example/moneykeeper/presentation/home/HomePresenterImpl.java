package com.example.moneykeeper.presentation.home;

import android.util.Log;

import com.example.domain.interactor.GetTransactionDataUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Transaction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.TimeUtils;

public class HomePresenterImpl implements HomeContract.Presenter {

    @Inject
    GetTransactionDataUseCase getTransactionDataUseCase;

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
        getTransactionDataUseCase.execute(new GetAllTransactionsOberserver(), new EmptyParam());
    }

    private class GetAllTransactionsOberserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            for (Transaction transaction : transactions) {
                String formattedDate = TimeUtils.convertMillisecondsToDateFormat(transaction.getDate());
                Log.e("TRANSACTIONDATA", transaction.getType() + " " +
                        transaction.getCategoryName() + " " + transaction.getAmount() + " " + formattedDate + " " + transaction.getMemo());
            }
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
}
