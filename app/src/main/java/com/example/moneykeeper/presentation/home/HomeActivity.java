package com.example.moneykeeper.presentation.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.example.moneykeeper.presentation.chart.ChartActivity;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    @Nullable
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_transaction)
    RecyclerView recyclerView;
    @Inject
    HomeContract.Presenter presenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupToolbar();
        setupNavigationView();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.item_sync:
                showToastMessage("Synced");
                break;
            case R.id.item_option:
                showToastMessage("Option");
                break;
            case R.id.item_help:
                showToastMessage("Help");
                break;
        }
        return true;
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_home);
    }

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public void setupNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(itemSelectedListener);

    }

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    public void setupRecyclerView() {
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(this, listener);
        homeRecyclerViewAdapter.setData(testData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeRecyclerViewAdapter);


    }

    private ItemClickListener<Transaction> listener = new ItemClickListener<Transaction>() {
        @Override
        public void onClickListener(int position, Transaction transaction) {
            showToastMessage("On Click");
        }

        @Override
        public void onLongClickListener(int position, Transaction transaction) {
            showToastMessage("On Long Click");
        }
    };
    private NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_overview:
                    openScreenByTag(TAG_OVERVIEW);
                    break;
                case R.id.item_summary:
                    openScreenByTag(TAG_SUMMARY);
                    break;
                case R.id.item_transaction:
                    openScreenByTag(TAG_TRANSACTION);
                    break;
                case R.id.item_chart:
                    openScreenByTag(TAG_CHART);
                    break;
                case R.id.item_setting:
                    openScreenByTag(TAG_SETTING);
                    break;
                default:
                    return false;
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
    };
    private static final int TAG_OVERVIEW = 0;
    private static final int TAG_SUMMARY = 1;
    private static final int TAG_TRANSACTION = 2;
    private static final int TAG_CHART = 3;
    private static final int TAG_SETTING = 4;

    private void openScreenByTag(int tag) {
        switch (tag) {
            case TAG_OVERVIEW:
                showToastMessage("OVERVIEW");
                break;
            case TAG_SUMMARY:
                showToastMessage("SUMMARY");
                break;
            case TAG_TRANSACTION:
                showToastMessage("TRANSACTION");
                break;
            case TAG_CHART:
                ChartActivity.startChartActivity(this);
                break;
            case TAG_SETTING:
                showToastMessage("SETTING");
                break;
        }
    }

    private List<Transaction> testData() {
        List<Transaction> mData = new ArrayList<>();
        mData.add(new Transaction("Bank", "1233456", "Viet", "22/03/2020"));
        return mData;
    }

}
