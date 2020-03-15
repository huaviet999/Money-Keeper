package com.example.moneykeeper.presentation.addcategory;

import javax.inject.Inject;

public class AddCategoryPresenterImpl implements AddCategoryContract.Presenter {
     AddCategoryContract.View mView;

    @Inject
    public AddCategoryPresenterImpl() {

    }

    @Override
    public void attachView(AddCategoryContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
