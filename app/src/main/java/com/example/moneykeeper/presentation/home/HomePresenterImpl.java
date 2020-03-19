package com.example.moneykeeper.presentation.home;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoryByNameUseCase;
import com.example.domain.interactor.transaction.DeleteTransactionsDataUseCase;
import com.example.domain.interactor.transaction.GetTransactionByIdUseCase;
import com.example.domain.interactor.transaction.GetTransactionsDataUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import utils.TimeUtils;

public class HomePresenterImpl implements HomeContract.Presenter {

    @Inject
    GetTransactionsDataUseCase getTransactionsDataUseCase;
    @Inject
    DeleteTransactionsDataUseCase deleteTransactionsDataUseCase;
    @Inject
    GetCategoryByNameUseCase getCategoryByNameUseCase;
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
        deleteTransactionsDataUseCase.execute(new DeleteTransactionsObserver(),new EmptyParam());
    }

    private class GetAllTransactionsObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            for (Transaction transaction : transactions) {
                String formattedDate = TimeUtils.convertMillisecondsToDateFormat(transaction.getDate());
                getCategoryByNameUseCase.execute(new GetCategotyByNameObserver(),transaction.getCategoryName());
                Log.e("TRANSACTIONDATA", transaction.getTransactionId() + " " + transaction.getType() + " " +
                        transaction.getCategoryName() + " " + transaction.getAmount() + " " + formattedDate + " " + transaction.getMemo());
            }
            mView.showTransactionsList(transactions);

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

    private class DeleteTransactionsObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
            Log.e("TRANSACTION","DELETE SUCCESSFUL");
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }
    }
    private class GetCategotyByNameObserver extends DisposableMaybeObserver<Category> {
        @Override
        public void onSuccess(@NonNull Category category) {


        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
