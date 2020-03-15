package com.example.moneykeeper.presentation.newtransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.category.CategoryActivity;
import com.maltaisn.calcdialog.CalcDialog;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Viet Hua on 15/3/2020
 */
public class NewTransactionActivity extends BaseActivity implements NewTransactionContract.View, CalcDialog.CalcDialogCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_income)
    TextView btnIcome;
    @BindView(R.id.btn_expense)
    TextView btnExpense;
    @BindView(R.id.edt_date)
    EditText edtDate;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.edt_memo)
    EditText edtMemo;
    @BindView(R.id.edt_category)
    EditText edtCategory;

    @Inject
    NewTransactionContract.Presenter presenter;


    public static void startNewTransactionActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, NewTransactionActivity.class);
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
    protected int getResLayoutId() {
        return R.layout.activity_newtransaction;
    }

    private void setupViews() {
        setupToolbar();
        testSetup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_confirm:
                showToastMessage("confirm");
                break;
        }
        return true;
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_new_transaction);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private final Calendar myCalendar = Calendar.getInstance();

    private void testSetup() {
        btnIcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIcome.setBackgroundColor(Color.parseColor("#2196F3"));
                btnIcome.setTextColor(Color.parseColor("#FFFFFF"));
                btnExpense.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnExpense.setTextColor(Color.parseColor("#000000"));
            }
        });
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIcome.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnIcome.setTextColor(Color.parseColor("#000000"));
                btnExpense.setBackgroundColor(Color.parseColor("#F44336"));
                btnExpense.setTextColor(Color.parseColor("#FFFFFF"));

            }
        });
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewTransactionActivity.this, dataPickerDialogListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        edtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryActivity.startCategoryActivity(NewTransactionActivity.this);
            }
        });
        edtAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcDialog calcDialog = new CalcDialog();
                calcDialog.getSettings().setInitialValue(null);
                calcDialog.getSettings().setExpressionEditable(true);
                calcDialog.getSettings().setExpressionShown(true);
                calcDialog.show(getSupportFragmentManager(),"calc_dialog");
            }
        });
    }

    @Override
    public void onValueEntered(int requestCode, @Nullable BigDecimal value) {
        edtAmount.setText(value.toString());
    }

    private DatePickerDialog.OnDateSetListener dataPickerDialogListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        }
    };
}
