package com.example.moneykeeper.presentation.detail;

import com.example.domain.model.Category;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

public interface DetailContract {
    interface View extends BaseView{
        void showTransactionDetail(Transaction transaction);
        void showCategoryImage(Category category);
    }
    interface Presenter extends BasePresenter<View>{
        void getTransactionDataById(int transactionId);
        void deleteTransactionById(int transactionId);
    }
}
