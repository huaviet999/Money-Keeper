package com.example.moneykeeper.presentation.newtransaction;

import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

public interface NewTransactionContract {
    interface View extends BaseView {
        void showDateFormat(String formattedDate);
        void showAmountValue(String value);
    }
    interface Presenter extends BasePresenter<View>{
        void getDateFormat(int year, int month, int dayOfMonth);
        void getAmountWithVietNamCurrency(String value);
        void getNewTransactionData(String type,String categoryName,String amount,String memo);
    }
}
