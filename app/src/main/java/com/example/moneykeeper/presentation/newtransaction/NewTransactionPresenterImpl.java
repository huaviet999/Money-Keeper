package com.example.moneykeeper.presentation.newtransaction;

import android.util.Log;

import javax.inject.Inject;

import utils.MathUtils;
import utils.TimeUtils;

public class NewTransactionPresenterImpl implements NewTransactionContract.Presenter {
    private long dateInMilliseconds = TimeUtils.getCurrentDateInMilliseconds();
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

    @Override
    public void getDateFormat(int year, int month, int dayOfMonth) {
        dateInMilliseconds = TimeUtils.getCurrentDateInMilliseconds();
        if(year != 0 && month != 0 && dayOfMonth != 0){
            dateInMilliseconds = TimeUtils.getDateFormatInMilliseconds(year, month, dayOfMonth);
        }
        String formattedDate = TimeUtils.convertMillisecondsToDateFormat(dateInMilliseconds);
        mView.showDateFormat(formattedDate);
    }

    @Override
    public void getAmountWithVietNamCurrency(String value) {
        String formattedValue = MathUtils.getNumberWithVietNamCurrency(value);
        mView.showAmountValue(formattedValue);
    }

    @Override
    public void getNewTransactionData(String type, String categoryName, String amount, String memo) {

        long realAmount = MathUtils.convertNumberFromStringToLong(amount);
        String date = TimeUtils.convertMillisecondsToDateFormat(dateInMilliseconds);

        Log.d("TransactionData", type + " " + categoryName + " " + realAmount + " " + dateInMilliseconds + " " + date   + " " + memo);
    }


}
