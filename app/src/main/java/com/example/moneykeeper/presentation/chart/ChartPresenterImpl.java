package com.example.moneykeeper.presentation.chart;

import com.example.domain.interactor.category.GetCategoriesByNameUseCase;
import com.example.domain.interactor.percent.GetSumAndPercentUseCase;
import com.example.domain.interactor.transaction.GetTransactionByTypeUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import timber.log.Timber;
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
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
    }

    @Override
    public void getTransactionListByType(String transactionType) {
        Timber.d("getTransactionListByType: %s", transactionType);
        getTransactionByTypeUseCase.execute(new GetTransactionsByTypeObserver(), transactionType);
    }

    private class GetTransactionsByTypeObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            Timber.d("onSuccess: %s", transactions.toString());
            getCategoriesByNameUseCase.execute(new GetCategoryByNameObserver(), transactions);
            long total = MathUtils.getTransactionSum(transactions);
            mView.showTotal(total);

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

    private class GetSumAndPercentListByCategoryObserver extends DisposableMaybeObserver<List<Percent>> {
        @Override
        public void onSuccess(@NonNull List<Percent> percents) {
            Timber.d("onSuccess: %s", percents.toString());
            mView.showPercentList(percents);
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

    private class GetCategoryByNameObserver extends DisposableMaybeObserver<List<Transaction>> {
        @Override
        public void onSuccess(@NonNull List<Transaction> transactions) {
            Timber.d("onSuccess: %s", transactions.toString());
            List<Percent> percents = new ArrayList<>();
            List<Category> categoryList = MoneyKeeperUtils.getUniqueCategoryList(transactions);
            for (Category category : categoryList) {

                percents.add(new Percent(category));
            }


            getSumAndPercentUseCase.execute(new GetSumAndPercentListByCategoryObserver(), percents);
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
