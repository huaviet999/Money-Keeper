package com.example.moneykeeper.presentation.detail;

import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

public interface DetailContract {
    interface View extends BaseView{
        void showTransactionDetail(Transaction transaction);
    }
    interface Presenter extends BasePresenter<View>{
        void getTransactionDataById(int transactionId);
    }
}
