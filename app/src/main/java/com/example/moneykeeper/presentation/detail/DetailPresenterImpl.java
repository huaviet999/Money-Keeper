package com.example.moneykeeper.presentation.detail;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoryByNameUseCase;
import com.example.domain.interactor.transaction.DeleteTransactionByIdUseCase;
import com.example.domain.interactor.transaction.GetTransactionByIdUseCase;
import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.model.Transaction;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import timber.log.Timber;
import utils.TimeUtils;

public class DetailPresenterImpl implements DetailContract.Presenter {
    DetailContract.View mView;

    @Inject
    GetTransactionByIdUseCase getTransactionByIdUseCase;
    @Inject
    DeleteTransactionByIdUseCase deleteTransactionByIdUseCase;
    @Inject
    GetCategoryByNameUseCase getCategoryByNameUseCase;

    @Inject
    public DetailPresenterImpl() {

    }

    @Override
    public void attachView(DetailContract.View view) {
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
    }

    @Override
    public void getTransactionDataById(int transactionId) {
        Timber.d("getTransactionDataById: %d", transactionId);
        getTransactionByIdUseCase.execute(new GetTransactionByIdObserver(), transactionId);
    }

    @Override
    public void deleteTransactionById(int transactionId) {
        Timber.d("deleteTransactionById: %d", transactionId);
        deleteTransactionByIdUseCase.execute(new DeleteTransactionByIdObserver(), transactionId);
    }

    private class GetTransactionByIdObserver extends DisposableMaybeObserver<Transaction> {
        @Override
        public void onSuccess(@NonNull Transaction transaction) {
            Timber.d("onSuccess: %s", transaction);
            getCategoryByNameUseCase.execute(new GetCategoryByNameObserver(), transaction);
            mView.showTransactionDetail(transaction);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e("onError: %s", e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }

    private class DeleteTransactionByIdObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e("onError: %s", e.getMessage());
        }
    }

    private class GetCategoryByNameObserver extends DisposableMaybeObserver<Transaction> {
        @Override
        public void onSuccess(@NonNull Transaction transaction) {
            Timber.d("onSuccess: %s", transaction);
            mView.showCategoryImage(transaction.getCategory());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e("onError: %s", e.getMessage());

        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }
}
