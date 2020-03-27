package com.example.moneykeeper.presentation.chart;

import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

import java.util.List;

public interface ChartContract {
    interface View extends BaseView {
        void showPercentList(List<Percent> percentList);
        void showTotal(long total);
    }

    interface Presenter extends BasePresenter<View> {
        void getTransactionListByType(String transactionType);
    }
}
