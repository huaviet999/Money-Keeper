package com.example.moneykeeper.presentation.home;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoriesByNameUseCase;
import com.example.domain.interactor.transaction.DeleteTransactionsDataUseCase;
import com.example.domain.interactor.transaction.GetTransactionsDataUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Transaction;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;

public class HomePresenterImpl implements HomeContract.Presenter {

    @Inject
    GetTransactionsDataUseCase getTransactionsDataUseCase;
    @Inject
    DeleteTransactionsDataUseCase deleteTransactionsDataUseCase;
    @Inject
    GetCategoriesByNameUseCase getCategoriesByNameUseCase;

    HashSet<Transaction> transactionList;
    private HomeContract.View mView;

    @Inject
    HomePresenterImpl() {
    }

    @Override
    public void attachView(HomeContract.View view) {
        transactionList = new HashSet<>();
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
