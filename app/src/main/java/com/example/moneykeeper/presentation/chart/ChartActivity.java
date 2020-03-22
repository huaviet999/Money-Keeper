package com.example.moneykeeper.presentation.chart;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.ExpenseType;
import com.example.domain.model.ModelTest1;
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

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

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
        setupPieChart();
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
        percentRecyclerViewAdapter.setData(testData());
        rvExpensePercent.setLayoutManager(linearLayoutManager);
        rvExpensePercent.setAdapter(percentRecyclerViewAdapter);

        expenseListRecyclerViewAdapter = new ExpenseListRecyclerViewAdapter(this, null);
        expenseListRecyclerViewAdapter.setData(testData());
        rvExpenseList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvExpenseList.setAdapter(expenseListRecyclerViewAdapter);
    }

    private void setupPieChart() {
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.strokeWidth(80);
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(50f, Color.parseColor("#00ff00"), "Income"))
                .addData(new SimplePieInfo(50f, Color.parseColor("#d34ede"), "CC"))
                .addData(new SimplePieInfo(50f, Color.parseColor("#ff45ed"), "SS"))
                .addData(new SimplePieInfo(50f, Color.parseColor("#ff0000"), "Expense"))
                .duration(1000);// dr// aw pie animation duration
        animatedPieView.applyConfig(config);
        animatedPieView.start();
    }

    private View.OnClickListener swapTransactionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            KEY_TRANSACTION_SELECTED = KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME)
                    ? Constants.KEY_EXPENSE : Constants.KEY_INCOME;

            tvTransactionType.setText(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? Constants.KEY_INCOME
                    : Constants.KEY_EXPENSE);
            presenter.getTransactionListByType(KEY_TRANSACTION_SELECTED);
        }
    };


    @Override
    public void showTransactionList(List<Transaction> transactionList) {

    }

    private List<ModelTest1> testData() {
        List<ModelTest1> mData = new ArrayList<>();
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Food));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Transport));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Shopping));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Bills));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Health));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Telephones));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Home));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Education));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Travel));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Insurance));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Social));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Sport));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Gift));
        mData.add(new ModelTest1("46.5%", "40000", ExpenseType.Others));
        return mData;
    }
}
