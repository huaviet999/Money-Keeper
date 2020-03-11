package com.example.moneykeeper.presentation.chart;

import javax.inject.Inject;

public class ChartPresenterImpl implements ChartContract.Presenter {
    ChartContract.View mView;

    @Inject
    public ChartPresenterImpl() {

    }

    @Override
    public void attachView(ChartContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
