package com.example.moneykeeper.presentation.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.domain.model.Account;
import com.example.domain.model.Category;
import com.example.domain.model.Record;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.BuildConfig;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.Navigator;
import com.example.moneykeeper.presentation.about.AboutActivity;
import com.example.moneykeeper.presentation.base.Constants;
import com.example.moneykeeper.presentation.category.CategoryActivity;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.example.moneykeeper.presentation.detail.DetailActivity;
import com.example.moneykeeper.presentation.newtransaction.NewTransactionActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_transaction)
    RecyclerView transactionRecyclerView;
    @BindView(R.id.rv_summary)
    RecyclerView summaryRecyclerView;
    @BindView(R.id.chart_bar)
    BarChart barChart;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nsv)
    NestedScrollView nestedScrollView;
    @Inject
    HomeContract.Presenter presenter;
    @Inject
    Navigator navigator;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TransactionRecyclerViewAdapter transactionRecyclerViewAdapter;
    private SummaryRecyclerViewAdapter summaryRecyclerViewAdapter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.getAllTransactionData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getTitle());
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.item_sync:
                presenter.deleteAllTransactionData();
                break;
            case R.id.item_option:
                break;
            case R.id.item_help:
                break;
        }
        return true;
    }

    @Override
    public void showTransactionList(List<Transaction> transactionsList) {
        Timber.d("showTransactionList: %s", transactionsList.toString());
        transactionRecyclerViewAdapter.setData(transactionsList);
    }


    @Override
    public void showSummaryList(List<Record> recordList) {
        Timber.d("showSummaryList: %s", recordList.toString());
        summaryRecyclerViewAdapter.setData(recordList);
    }

    private void setupViews() {
        Timber.d("setupViews");
        setupToolbar();
        setupNavigationView();
        setupRecyclerView();
        setupFab();
    }


    private void setupToolbar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_home);
    }

    private void setupNavigationView() {
        Timber.d("setupNavigationView");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Timber.d("onDrawerOpened");
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(itemSelectedListener);

    }
    
    private void setupRecyclerView() {
        Timber.d("setupRecyclerView");
        //TRANSACTION RECYCLER VIEW
        transactionRecyclerViewAdapter = new TransactionRecyclerViewAdapter(this, transactionListener);
        transactionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        transactionRecyclerView.setAdapter(transactionRecyclerViewAdapter);

        //SUMMARY RECYCLER VIEW
        summaryRecyclerViewAdapter = new SummaryRecyclerViewAdapter(this, accountListener);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        summaryRecyclerView.setAdapter(summaryRecyclerViewAdapter);

    }

    //** BAR CHART NOT USED IN APP **//
    private void setupBarChart() {
        Timber.d("setupBarChart");
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 10000000));
        barEntries.add(new BarEntry(2, 500000));
        barEntries.add(new BarEntry(3, 500000));
        barEntries.add(new BarEntry(4, 500000));
        barEntries.add(new BarEntry(5, 500000));
        barEntries.add(new BarEntry(6, 500000));
        barEntries.add(new BarEntry(7, 500000));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#00ff00"));
        colorList.add(Color.parseColor("#ff0000"));
        barDataSet.setColors(colorList);
        barDataSet.setValueTextSize(10f);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.8f);

        barChart.animateY(2000);
        barChart.setPinchZoom(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setData(data);
    }

    private void setupFab() {
        Timber.d("setupFab");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.d("Fab Clicked");
                openScreenByTag(Constants.TAG_NEW_TRANSACTION);
            }
        });

        //Show-hide fab when scrolling
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Timber.d("onScrollChange");
                if (i1 > i3) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }

        });
    }

    private ItemClickListener<Transaction> transactionListener = new ItemClickListener<Transaction>() {
        @Override
        public void onClickListener(int position, Transaction transaction) {
            Timber.d("Transaction onClicked: %d", position);
            int transactionId = transactionRecyclerViewAdapter.getItem(position).getTransactionId();
            DetailActivity.startDetailActivity(HomeActivity.this, transactionId);

        }

        @Override
        public void onLongClickListener(int position, Transaction transaction) {
            Timber.d("Transaction onLongClicked: %d", position);
        }
    };

    private ItemClickListener<Record> accountListener = new ItemClickListener<Record>() {
        @Override
        public void onClickListener(int position, Record record) {
            Timber.d("Record onClicked: %d", position);

        }

        @Override
        public void onLongClickListener(int position, Record record) {
            Timber.d("Record onLongClicked: %d", position);
        }
    };

    private NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Timber.d("onNavigationItemSelected: %s", item.getTitle());
            switch (item.getItemId()) {
                case R.id.item_overview:
                    openScreenByTag(Constants.TAG_OVERVIEW);
                    break;
                case R.id.item_summary:
                    openScreenByTag(Constants.TAG_SUMMARY);
                    break;
                case R.id.item_transaction:
                    openScreenByTag(Constants.TAG_TRANSACTION);
                    break;
                case R.id.item_chart:
                    openScreenByTag(Constants.TAG_CHART);
                    break;
                case R.id.item_setting:
                    openScreenByTag(Constants.TAG_SETTING);
                    break;
                case R.id.item_about:
                    openScreenByTag(Constants.TAG_ABOUT);
                    break;
                default:
                    return false;
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
    };


    private void openScreenByTag(String tag) {
        Timber.d("openScreenByTag: %s", tag);
        switch (tag) {
            case Constants.TAG_OVERVIEW:
                break;
            case Constants.TAG_SUMMARY:
                navigator.openSummaryActivity(this);
                break;
            case Constants.TAG_TRANSACTION:
                showToastMessage("TRANSACTION");
                break;
            case Constants.TAG_CHART:
                navigator.openChartActivity(this);
                break;
            case Constants.TAG_SETTING:
                showToastMessage("SETTING");
                break;
            case Constants.TAG_NEW_TRANSACTION:
                NewTransactionActivity.startNewTransactionActivity(this);
                break;
            case Constants.TAG_ABOUT:
                navigator.openAboutActivity(this);
                break;

        }
    }
}
