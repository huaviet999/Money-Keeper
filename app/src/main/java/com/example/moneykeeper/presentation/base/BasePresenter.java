package com.example.moneykeeper.presentation.base;

public interface BasePresenter<V> {
    void attachView(V view);
    void dropView();
}
