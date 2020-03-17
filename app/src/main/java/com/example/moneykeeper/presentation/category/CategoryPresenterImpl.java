package com.example.moneykeeper.presentation.category;

import android.util.Log;

import com.example.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryPresenterImpl implements CategoryContract.Presenter {
    CategoryContract.View mView;

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
    public void setDefaultCategoriesList() {
        mView.showCategoriesList(Category.getDefaultCategoriesList());
    }
}
