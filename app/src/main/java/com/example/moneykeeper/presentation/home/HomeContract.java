package com.example.moneykeeper.presentation.home;

import com.example.domain.model.Category;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

import java.util.HashSet;
import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showTransactionList(List<Transaction> transactionsList);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllTransactionData();
        void deleteAllTransactionData();
    }
}
