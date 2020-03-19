package com.example.moneykeeper.presentation.detail;

import com.example.domain.interactor.transaction.GetTransactionByIdUseCase;
import com.example.domain.model.Transaction;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;

public class DetailPresenterImpl implements DetailContract.Presenter {
    DetailContract.View mView;

    @Inject
    GetTransactionByIdUseCase getTransactionByIdUseCase;

    @Inject
    public DetailPresenterImpl() {

    }

    @Override
    public void attachView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getTransactionDataById(int transactionId) {
        getTransactionByIdUseCase.execute(new GetTransactionByIdObserver(), transactionId);
    }

    private class GetTransactionByIdObserver extends DisposableMaybeObserver<Transaction> {
        @Override
        public void onSuccess(@NonNull Transaction transaction) {
            mView.showTransactionDetail(transaction);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
