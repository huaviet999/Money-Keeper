package com.example.moneykeeper.presentation.newtransaction;

import android.util.Log;

import com.example.domain.interactor.SaveTransactionUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import utils.MathUtils;
import utils.TimeUtils;

public class NewTransactionPresenterImpl implements NewTransactionContract.Presenter {
    private long dateInMilliseconds;
    private boolean currentDateFlag = false;
    NewTransactionContract.View mView;

    @Inject
    SaveTransactionUseCase saveTransactionUseCase;

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
        saveTransactionUseCase.dispose();
    }

    @Override
    public void getDateFormat(int year, int month, int dayOfMonth) {
        if(!currentDateFlag){ //Make sure current date only show for the first time
            dateInMilliseconds = TimeUtils.getCurrentDateInMilliseconds();
            currentDateFlag = true;
        }
        if (year != 0 && month != 0 && dayOfMonth != 0) {
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
        saveTransactionUseCase.execute(new SaveTransactionObserver(), new SaveTransactionUseCase.Param(type, categoryName, memo, realAmount, dateInMilliseconds));
    }

    private class SaveTransactionObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            mView.onSaveTransactionSucceed();
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }
    }

}
