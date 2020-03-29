package com.example.moneykeeper.presentation.category;

import android.util.Log;

import com.example.domain.interactor.category.GetCategoriesByTypeUseCase;
import com.example.domain.interactor.category.GetCategoriesUseCase;
import com.example.domain.model.Category;
import com.example.domain.model.EmptyParam;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import timber.log.Timber;

public class CategoryPresenterImpl implements CategoryContract.Presenter {

    CategoryContract.View mView;

    @Inject
    GetCategoriesByTypeUseCase getCategoriesByTypeUseCase;

    @Inject
    public CategoryPresenterImpl() {

    }

    @Override
    public void attachView(CategoryContract.View view) {
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
    }

    @Override
    public void getDefaultCategoriesListByType(String type) {
        Timber.d("getDefaultCatgoriesListByType: %s", type);
        getCategoriesByTypeUseCase.execute(new GetCategoriesByTypeObserver(), type);
    }

    private class GetCategoriesByTypeObserver extends DisposableMaybeObserver<List<Category>> {
        @Override
        public void onSuccess(@NonNull List<Category> categoryList) {
            Timber.d("onSuccess: %s", categoryList.toString());
            mView.showCategoriesList(categoryList);
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
