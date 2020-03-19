package com.example.moneykeeper.presentation.category;

import android.util.Log;

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
    GetCategoriesUseCase getCategoriesUseCase;

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
    public void getDefaultCategoriesList() {
        getCategoriesUseCase.execute(new GetCategoriesListObserver(), new EmptyParam());
    }

    private class GetCategoriesListObserver extends DisposableMaybeObserver<List<Category>> {
        @Override
        public void onSuccess(@NonNull List<Category> categories) {
            for (Category category : categories) {
                Log.e("CATEGORYDATA", category.getCategoryId() + " "
                        + category.getName() + " " + category.getNImage() + " " + category.getCImage());
            }
            mView.showCategoriesList(categories);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
