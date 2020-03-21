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

public class CategoryPresenterImpl implements CategoryContract.Presenter {
    CategoryContract.View mView;

    @Inject
    GetCategoriesByTypeUseCase getCategoriesByTypeUseCase;

    @Inject
    public CategoryPresenterImpl() {

    }

    @Override
    public void attachView(CategoryContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getDefaultCategoriesListByType(String type) {
        getCategoriesByTypeUseCase.execute(new GetCategoriesByTypeObserver(), type);
    }

    private class GetCategoriesByTypeObserver extends DisposableMaybeObserver<List<Category>> {
        @Override
        public void onSuccess(@NonNull List<Category> categoryList) {
            mView.showCategoriesList(categoryList);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
