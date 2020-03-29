package com.example.moneykeeper.presentation.newtransaction;

import com.example.domain.interactor.transaction.SaveTransactionUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import timber.log.Timber;
import utils.MathUtils;
import utils.TimeUtils;

public class NewTransactionPresenterImpl implements NewTransactionContract.Presenter {
    @Inject
    SaveTransactionUseCase saveTransactionUseCase;

    NewTransactionContract.View mView;
    private long dateInMilliseconds;
    private boolean currentDateFlag = false;

    @Inject
    public NewTransactionPresenterImpl() {
        Timber.d("NewTransactionPresenterImpl Constructor");
    }


    @Override
    public void attachView(NewTransactionContract.View view) {
        Timber.d("attachView");
        mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        mView = null;
        saveTransactionUseCase.dispose();
    }

    @Override
    public void getDateFormat(int year, int month, int dayOfMonth) {
        Timber.d("getDateFormat");
        if (!currentDateFlag) { //Make sure current date only show for the first time
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
        Timber.d("getAmountWithVietNamCurrency");
        String formattedValue = MathUtils.getNumberWithVietNamCurrency(value);
        mView.showAmountValue(formattedValue);
    }

    @Override
    public void getNewTransactionData(String type, String categoryName, String amount, String memo) {
        Timber.d("getNewTransactionData: %s , %s , %s , %s", type, categoryName, amount, memo);
        long realAmount = MathUtils.convertNumberFromStringToLong(amount);
        saveTransactionUseCase.execute(new SaveTransactionObserver(),
                new SaveTransactionUseCase.Param(type, categoryName, memo, realAmount, dateInMilliseconds));
    }

    private class SaveTransactionObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.onSaveTransactionSucceed();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e("onError: %s", e.getMessage());
        }
    }

}
