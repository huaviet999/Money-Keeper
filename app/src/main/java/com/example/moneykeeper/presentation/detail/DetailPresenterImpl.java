package com.example.moneykeeper.presentation.detail;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoryByNameUseCase;
import com.example.domain.interactor.transaction.DeleteTransactionByIdUseCase;
import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.model.Transaction;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.TimeUtils;

public class DetailPresenterImpl implements DetailContract.Presenter {
    DetailContract.View mView;

    @Inject
    GetTransactionByTypeUseCase getTransactionByTypeUseCase;
    @Inject
    DeleteTransactionByIdUseCase deleteTransactionByIdUseCase;
    @Inject
    GetCategoryByNameUseCase getCategoryByNameUseCase;

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
        getTransactionByTypeUseCase.execute(new GetTransactionByIdObserver(), transactionId);
    }

    @Override
    public void deleteTransactionById(int transactionId) {
        deleteTransactionByIdUseCase.execute(new DeleteTransactionByIdObserver(), transactionId);
    }

    private class GetTransactionByIdObserver extends DisposableMaybeObserver<Transaction> {
        @Override
        public void onSuccess(@NonNull Transaction transaction) {
            getCategoryByNameUseCase.execute(new GetCategoryByNameObserver(), transaction);
            mView.showTransactionDetail(transaction);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private class DeleteTransactionByIdObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Log.d("TRANSACTION", "DELTE SUCCESSFUL");
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }
    }

    private class GetCategoryByNameObserver extends DisposableMaybeObserver<Transaction> {
        @Override
        public void onSuccess(@NonNull Transaction transaction) {
            int test = TimeUtils.getYearFromMilliseconds(transaction.getDate());
            Log.d("MONTH",String.valueOf(test));
            mView.showCategoryImage(transaction.getCategory());
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
