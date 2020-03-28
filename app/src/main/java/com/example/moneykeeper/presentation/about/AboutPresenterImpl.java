package com.example.moneykeeper.presentation.about;

import javax.inject.Inject;

public class AboutPresenterImpl implements AboutContract.Presenter {

    AboutContract.View mView;

    @Inject
    public AboutPresenterImpl() {

    }


    @Override
    public void attachView(AboutContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
