package com.example.moneykeeper.presentation.summary;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.domain.model.Account;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.example.moneykeeper.presentation.home.SummaryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class SummaryActivity extends BaseActivity implements SummaryContract.View {
    @Inject
    SummaryContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_summary)
    RecyclerView rvSummary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
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
        return R.layout.activity_summary;
    }

    private void setupToolbar() {
        toolbar.inflateMenu(R.menu.menu_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    private SummaryRecyclerViewAdapter summaryRecyclerViewAdapter;

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        summaryRecyclerViewAdapter = new SummaryRecyclerViewAdapter(this, listener);
//        summaryRecyclerViewAdapter.setData(testData2());
        rvSummary.setLayoutManager(linearLayoutManager);
        rvSummary.setAdapter(summaryRecyclerViewAdapter);
    }

    private ItemClickListener<Account> listener = new ItemClickListener<Account>() {
        @Override
        public void onClickListener(int position, Account account) {
            showToastMessage("On click");
        }

        @Override
        public void onLongClickListener(int position, Account account) {
            showToastMessage("On Long click");
        }
    };

    private List<Account> testData2() {
        List<Account> mData = new ArrayList<>();
        mData.add(new Account(1000000, 300000));
        mData.add(new Account(2000, 500000));
        return mData;
    }
}
