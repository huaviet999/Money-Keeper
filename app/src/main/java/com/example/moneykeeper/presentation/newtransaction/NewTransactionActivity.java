package com.example.moneykeeper.presentation.newtransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.Constants;
import com.example.moneykeeper.presentation.category.CategoryActivity;
import com.maltaisn.calcdialog.CalcDialog;

import java.math.BigDecimal;
import java.util.Calendar;
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

/**
 * Created by Viet Hua on 15/3/2020
 */
public class NewTransactionActivity extends BaseActivity implements NewTransactionContract.View, CalcDialog.CalcDialogCallback {
    public String KEY_TRANSACTION_SELECTED = Constants.KEY_INCOME; //Default transaction mode is Income
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_income)
    TextView btnIncome;
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
    @BindView(R.id.txt_date)
    TextView tvDate;
    @BindView(R.id.txt_amount)
    TextView tvAmount;
    @BindView(R.id.txt_category)
    TextView tvCategory;
    @BindView(R.id.txt_memo)
    TextView tvMemo;

    @Inject
    NewTransactionContract.Presenter presenter;


    public static void startNewTransactionActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, NewTransactionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Make sure this activity doesn't create repeeatly
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
        presenter.getDateFormat(0, 0, 0); //Get current date

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CategoryActivity.CATEGORY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                edtCategory.setText(data.getStringExtra(CategoryActivity.KEY_CATEGORY_NAME));
            }
        }
    }

    private void setupViews() {
        setupToolbar();
        initViews();
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
                doTransactionData();
                break;
        }
        return true;
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_new_transaction);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    private void setupCalculator() {
        CalcDialog calcDialog = new CalcDialog();
        calcDialog.getSettings().setInitialValue(null);
        calcDialog.getSettings().setExpressionEditable(true);
        calcDialog.getSettings().setExpressionShown(true);
        calcDialog.show(getSupportFragmentManager(), "calc_dialog");
    }

    private void setupDatePickerDialog() {
        new DatePickerDialog(NewTransactionActivity.this, dataPickerDialogListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private final Calendar myCalendar = Calendar.getInstance();

    private void initViews() {

        btnIncome.setOnClickListener(onClickListener);
        btnExpense.setOnClickListener(onClickListener);


        //Show date dialog
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupDatePickerDialog();
            }
        });


        edtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryActivity.startCategoryActivityForResult(NewTransactionActivity.this);
            }
        });


        edtAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupCalculator();
            }
        });
    }


    private void transactionColorChange() {
        int whiteColor = ResourcesCompat.getColor(getResources(), R.color.white, null);
        int blackColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
        int incomeButtonColor = ResourcesCompat.getColor(getResources(), R.color.income_button_color, null);
        int expenseButtonColor = ResourcesCompat.getColor(getResources(), R.color.expense_button_color, null);

        btnIncome.setBackgroundColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? incomeButtonColor : whiteColor);
        btnIncome.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? whiteColor : blackColor);
        btnExpense.setBackgroundColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_EXPENSE) ? expenseButtonColor : whiteColor);
        btnExpense.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_EXPENSE) ? whiteColor : blackColor);

        tvDate.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? incomeButtonColor : expenseButtonColor);
        tvAmount.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? incomeButtonColor : expenseButtonColor);
        tvCategory.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? incomeButtonColor : expenseButtonColor);
        tvMemo.setTextColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? incomeButtonColor : expenseButtonColor);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? R.color.income_button_color : R.color.expense_button_color)));

        edtMemo.setCompoundDrawablesWithIntrinsicBounds(0, 0, KEY_TRANSACTION_SELECTED.equals(Constants.KEY_INCOME) ? R.drawable.ic_photo_camera_blue_24dp : R.drawable.ic_photo_camera_red_24dp, 0);
    }


    @Override
    public void onValueEntered(int requestCode, @Nullable BigDecimal value) {
        presenter.getAmountWithVietNamCurrency(value.toString());
    }

    @Override
    public void showAmountValue(String value) {
        edtAmount.setText(value);
    }

    private DatePickerDialog.OnDateSetListener dataPickerDialogListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            presenter.getDateFormat(i, i1, i2);
        }
    };

    @Override
    public void showDateFormat(String formattedDate) {
        edtDate.setText(formattedDate);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_income) {
                KEY_TRANSACTION_SELECTED = Constants.KEY_INCOME;
            } else {
                KEY_TRANSACTION_SELECTED = Constants.KEY_EXPENSE;
            }
            transactionColorChange();
        }
    };

    @Override
    public void onSaveTransactionSucceed() {
        showToastMessage("SAVE SUCCESSFULLY");
        finish();
    }

    public void doTransactionData() {
        String type = KEY_TRANSACTION_SELECTED;
        String category = edtCategory.getText().toString();
        String amount = edtAmount.getText().toString();
        String memo = edtMemo.getText().toString();
        presenter.getNewTransactionData(type, category, amount, memo);
    }
}
