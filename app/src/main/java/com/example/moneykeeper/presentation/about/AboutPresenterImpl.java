package com.example.moneykeeper.presentation.about;

import javax.inject.Inject;

import timber.log.Timber;

public class AboutPresenterImpl implements AboutContract.Presenter {

    AboutContract.View mView;

    @Inject
    public AboutPresenterImpl() {

    }


    @Override
    public void attachView(AboutContract.View view) {
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
    }
}
