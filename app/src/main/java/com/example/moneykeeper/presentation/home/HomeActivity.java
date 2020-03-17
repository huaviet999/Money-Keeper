package com.example.moneykeeper.presentation.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.domain.model.Account;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.Navigator;
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

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    private void setupViews() {
        setupToolbar();
        setupNavigationView();
        setupRecyclerView();
        setupBarChart();
        setupFab();
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

    private TransactionRecyclerViewAdapter transactionRecyclerViewAdapter;
    private SummaryRecyclerViewAdapter summaryRecyclerViewAdapter;

    public void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //TRANSACTION RECYCLER VIEW
        transactionRecyclerViewAdapter = new TransactionRecyclerViewAdapter(this, transactionListener);
        transactionRecyclerViewAdapter.setData(testData());
        transactionRecyclerView.setLayoutManager(linearLayoutManager);
        transactionRecyclerView.setAdapter(transactionRecyclerViewAdapter);

        //SUMMARY RECYCLER VIEW
        summaryRecyclerViewAdapter = new SummaryRecyclerViewAdapter(this, accountListener);
        summaryRecyclerViewAdapter.setData(testData2());
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        summaryRecyclerView.setAdapter(summaryRecyclerViewAdapter);

    }

    public void setupBarChart() {
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreenByTag(Constants.TAG_NEW_TRANSACTION);
            }
        });

        //Show-hide fab when scrolling
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
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
            openScreenByTag(Constants.TAG_DETAIL);
        }

        @Override
        public void onLongClickListener(int position, Transaction transaction) {
            showToastMessage("On Long Click");
        }
    };
    private ItemClickListener<Account> accountListener = new ItemClickListener<Account>() {
        @Override
        public void onClickListener(int position, Account transaction) {
            showToastMessage("On Click");
        }

        @Override
        public void onLongClickListener(int position, Account transaction) {
            showToastMessage("On Long Click");
        }
    };
    private NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                default:
                    return false;
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
    };


    private void openScreenByTag(String tag) {
        switch (tag) {
            case Constants.TAG_OVERVIEW:
                showToastMessage("OVERVIEW");
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
                NewTransactionActivity.startNewTransactionActivity(this,"");
                break;
            case Constants.TAG_DETAIL:
                DetailActivity.startDetailActivity(this);
                break;

        }
    }

    private List<Transaction> testData() {
        List<Transaction> mData = new ArrayList<>();
        mData.add(new Transaction("Bank", "1233456", "Viet", "22/03/2020"));
        return mData;
    }

    private List<Account> testData2() {
        List<Account> mData = new ArrayList<>();
        mData.add(new Account(1000000, 300000));
        mData.add(new Account(2000, 500000));
        return mData;
    }
}
