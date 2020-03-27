package com.example.moneykeeper.presentation.chart;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.ExpenseType;
import com.example.domain.model.ModelTest1;
import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.Constants;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import utils.MathUtils;
import utils.MoneyKeeperUtils;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class ChartActivity extends BaseActivity implements ChartContract.View {
    public String KEY_TRANSACTION_SELECTED = Constants.KEY_INCOME; //Default income show
    @Inject
    ChartContract.Presenter presenter;
    @BindView(R.id.chart_pie)
    AnimatedPieView animatedPieView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_expense_percent)
    RecyclerView rvExpensePercent;
    @BindView(R.id.rv_expense)
    RecyclerView rvExpenseList;
    @BindView(R.id.btn_transaction_swap)
    ImageView btnTransactionSwap;
    @BindView(R.id.txt_transaction_type)
    TextView tvTransactionType;
    @BindView(R.id.txt_total_number)
    TextView tvTotal;
    @BindView(R.id.txt_percent_title)
    TextView tvPercentTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupViews();

    }


    @Override
    protected int getResLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.getTransactionListByType(KEY_TRANSACTION_SELECTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
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
                break;
        }
        return true;
    }

    private void setupViews() {
        setupToolbar();
        setupRecyclerView();
        btnTransactionSwap.setOnClickListener(swapTransactionListener);
    }

    private void setupToolbar() {
        toolbar.inflateMenu(R.menu.menu_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private PercentRecyclerViewAdapter percentRecyclerViewAdapter;
    private ExpenseListRecyclerViewAdapter expenseListRecyclerViewAdapter;

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        percentRecyclerViewAdapter = new PercentRecyclerViewAdapter(this, null);
        rvExpensePercent.setLayoutManager(linearLayoutManager);
        rvExpensePercent.setAdapter(percentRecyclerViewAdapter);

        expenseListRecyclerViewAdapter = new ExpenseListRecyclerViewAdapter(this, null);
        rvExpenseList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvExpenseList.setAdapter(expenseListRecyclerViewAdapter);
    }

    private void setupPieChart(List<Percent> percentList) {
        Log.d("SetupPieChart", "start");
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.strokeWidth(80);
        config.startAngle(-90);
        config.duration(1000);
        for (Percent percent : percentList) {
            config.addData(new SimplePieInfo(percent.getSum(), Color.parseColor(percent.getCategory().getColorId()), percent.getCategory().getName()));
        }
        animatedPieView.applyConfig(config);
        animatedPieView.start();
    }

    private View.OnClickListener swapTransactionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            KEY_TRANSACTION_SELECTED = KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME)
                    ? Constants.KEY_EXPENSE : Constants.KEY_INCOME;

            changeTextViewTitle();
            presenter.getTransactionListByType(KEY_TRANSACTION_SELECTED);
        }
    };

    private void changeTextViewTitle() {
        tvTransactionType.setText(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? Constants.KEY_INCOME
                : Constants.KEY_EXPENSE);

        tvTransactionType.setTextColor(ResourcesCompat.getColor(getResources(), KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ?
                R.color.income_button_color : R.color.expense_button_color, null));

        tvPercentTitle.setText(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? getString(R.string.income)
                : getString(R.string.expense));

        tvPercentTitle.setTextColor(ResourcesCompat.getColor(getResources(), KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ?
                R.color.income_button_color : R.color.expense_button_color, null));
    }

    @Override
    public void showPercentList(List<Percent> percentList) {
        percentList = MoneyKeeperUtils.sortListBySum(percentList);
        percentRecyclerViewAdapter.setData(percentList);
        expenseListRecyclerViewAdapter.setData(percentList);
        setupPieChart(percentList);

    }

    @Override
    public void showTotal(long total) {
        tvTotal.setText(MathUtils.getFormatNumberFromLongWithoutCurrency(total));

    }
}
