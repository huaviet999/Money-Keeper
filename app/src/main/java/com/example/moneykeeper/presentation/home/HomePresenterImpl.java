package com.example.moneykeeper.presentation.home;

import javax.inject.Inject;

public class HomePresenterImpl implements HomeContract.Presenter {
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
}
