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
import timber.log.Timber;

public class SummaryActivity extends BaseActivity implements SummaryContract.View {
    private static final String TAG = SummaryActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_summary)
    RecyclerView rvSummary;

    @Inject
    SummaryContract.Presenter presenter;

    private SummaryRecyclerViewAdapter summaryRecyclerViewAdapter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_summary;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        Timber.d("onStart");
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }


    private void setupToolbar() {
        Timber.d("setupToolbar");
        toolbar.inflateMenu(R.menu.menu_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        Timber.d("setupRecyclerView");
//        summaryRecyclerViewAdapter = new SummaryRecyclerViewAdapter(this, listener);
        rvSummary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSummary.setAdapter(summaryRecyclerViewAdapter);
    }

    private ItemClickListener<Account> listener = new ItemClickListener<Account>() {
        @Override
        public void onClickListener(int position, Account account) {
            Timber.d("onClick: %d", position);
        }

        @Override
        public void onLongClickListener(int position, Account account) {
            Timber.d("onLongClick: %d", position);

        }
    };

}
