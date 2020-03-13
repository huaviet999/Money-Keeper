package com.example.moneykeeper.presentation.summary;

import javax.inject.Inject;

public class SummaryPresenterImpl implements SummaryContract.Presenter {
    SummaryContract.View mView;

    @Inject
    public SummaryPresenterImpl() {

    }

    @Override
    public void attachView(SummaryContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
