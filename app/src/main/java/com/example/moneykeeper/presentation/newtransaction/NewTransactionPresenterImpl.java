package com.example.moneykeeper.presentation.newtransaction;

import javax.inject.Inject;

public class NewTransactionPresenterImpl implements NewTransactionContract.Presenter {
    NewTransactionContract.View mView;

    @Inject
    public NewTransactionPresenterImpl() {
    }

    @Override
    public void attachView(NewTransactionContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
