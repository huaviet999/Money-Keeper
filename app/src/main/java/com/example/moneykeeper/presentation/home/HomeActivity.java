package com.example.moneykeeper.presentation.home;

import android.os.Bundle;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import dagger.android.AndroidInjection;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomeContract.Presenter presenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}
