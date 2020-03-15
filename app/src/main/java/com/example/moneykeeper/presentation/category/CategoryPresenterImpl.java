package com.example.moneykeeper.presentation.category;

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
}
