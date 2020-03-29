package com.example.moneykeeper.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.Category;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.Constants;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import timber.log.Timber;
import utils.MathUtils;
import utils.TimeUtils;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    public static final String KEY_TRANSACTION_ID = "KEY_TRANSACTION_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_category)
    TextView tvCategory;
    @BindView(R.id.txt_transaction_type)
    TextView tvTransactionType;
    @BindView(R.id.txt_amount)
    TextView tvAmount;
    @BindView(R.id.txt_date)
    TextView tvDate;
    @BindView(R.id.txt_memo)
    TextView tvMemo;
    @BindView(R.id.img_category)
    ImageView imgCategory;
    @Inject
    DetailContract.Presenter presenter;

    private int transactionId;

    public static void startDetailActivity(AppCompatActivity activity, int transactionId) {
        Timber.d("startDetailActivity");
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY_TRANSACTION_ID, transactionId);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        transactionId = bundle.getInt(KEY_TRANSACTION_ID);
        setupViews();
    }


    @Override
    protected void onStart() {
        Timber.d("onStart");
        super.onStart();
        presenter.attachView(this);
        presenter.getTransactionDataById(transactionId);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_delete:
                presenter.deleteTransactionById(transactionId);
                finish();
                break;
            case R.id.item_edit:
                showToastMessage("Edit");
                break;
        }
        return true;
    }

    @Override
    public void showTransactionDetail(Transaction transaction) {
        Timber.d("showTransactionDetail");
        String date = TimeUtils.convertMillisecondsToDateFormat(transaction.getDate());
        tvCategory.setText(transaction.getCategory().getName());

        tvMemo.setText(transaction.getMemo());
        tvDate.setText(date);
        changeTextColor(transaction);
    }

    @Override
    public void showCategoryImage(Category category) {
        Timber.d("showCategoryImage");
        imgCategory.setImageResource(getResources().getIdentifier(category.getCImage(), "drawable", getPackageName()));
    }


    private void setupViews() {
        Timber.d("setupViews");
        setupToolBar();
    }

    private void setupToolBar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }


    private void changeTextColor(Transaction transaction) {
        Timber.d("changeTextColor");
        String amount = MathUtils.getFormatNumberFromLong(transaction.getAmount());
        tvAmount.setTextColor(transaction.getType().equals(Constants.KEY_INCOME) ?
                ResourcesCompat.getColor(getResources(), R.color.income_button_color, null) :
                ResourcesCompat.getColor(getResources(), R.color.expense_button_color, null));
        tvAmount.setText(transaction.getType().equals(Constants.KEY_INCOME) ? amount : "-" + amount);

        tvTransactionType.setTextColor(transaction.getType().equals(Constants.KEY_INCOME) ?
                ResourcesCompat.getColor(getResources(), R.color.income_button_color, null) :
                ResourcesCompat.getColor(getResources(), R.color.expense_button_color, null));
        tvTransactionType.setText(transaction.getType());
    }
}
