package com.example.moneykeeper.presentation.base;

public interface ItemClickListener<T> {
    void onClickListener(int position,T t);
    void onLongClickListener(int position,T t);
}
