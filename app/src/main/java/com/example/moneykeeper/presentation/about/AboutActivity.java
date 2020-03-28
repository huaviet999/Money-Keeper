package com.example.moneykeeper.presentation.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class AboutActivity extends BaseActivity implements AboutContract.View {


    @Inject
    AboutContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
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

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.btn_close)
    public void onCloseButtonClick() {
        finish();
    }

}
