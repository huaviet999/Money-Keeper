package com.example.moneykeeper.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    DetailContract.Presenter presenter;

    public static void startDetailActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupViews();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_delete:
                showToastMessage("Delete");
                break;
            case R.id.item_edit:
                showToastMessage("Edit");
                break;
        }
        return true;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_detail;
    }

    private void setupViews() {
        setupToolBar();
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
