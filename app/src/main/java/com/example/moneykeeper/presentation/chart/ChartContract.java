package com.example.moneykeeper.presentation.chart;

import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

import java.util.List;

public interface ChartContract {
    interface View extends BaseView{
        void showTransactionList(List<Transaction> transactionList);
    }
    interface Presenter extends BasePresenter<View>{
            void getAllTransactionList();
    }
}
