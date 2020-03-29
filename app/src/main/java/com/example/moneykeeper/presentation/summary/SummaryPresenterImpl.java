package com.example.moneykeeper.presentation.summary;

import javax.inject.Inject;

import timber.log.Timber;

public class SummaryPresenterImpl implements SummaryContract.Presenter {
    SummaryContract.View mView;

    @Inject
    public SummaryPresenterImpl() {
        Timber.d("SummaryPresenterImpl Constructor");
    }

    @Override
    public void attachView(SummaryContract.View view) {
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
    }
}
