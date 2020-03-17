package com.example.moneykeeper.presentation.category;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.domain.model.Category;
import com.example.domain.model.ExpenseType;
import com.example.domain.model.ModelTest1;
import com.example.moneykeeper.R;
import com.example.moneykeeper.myapp.MyApp;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.example.moneykeeper.presentation.newtransaction.NewTransactionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Viet Hua on 3/15/2020
 */
public class CategoryActivity extends BaseActivity implements CategoryContract.View {
    public static final int CATEGORY_REQUEST_CODE = 1;
    public static final String KEY_CATEGORY_NAME = "KEY_CATEGORY_NAME";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

    public static void startCategoryActivityForResult(AppCompatActivity activity) {
        Intent intent = new Intent(activity,CategoryActivity.class);
        activity.startActivityForResult(intent,CATEGORY_REQUEST_CODE);
    }

    @Inject
    CategoryContract.Presenter presenter;

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
        presenter.setDefaultCategoriesList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }


    private void setupViews() {
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        toolbar.inflateMenu(R.menu.menu_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(this, listener);
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.setAdapter(categoryRecyclerViewAdapter);

    }

    private ItemClickListener<Category> listener = new ItemClickListener<Category>() {
        @Override
        public void onClickListener(int position, Category mCategory) {
            Intent intent = new Intent();
            Category category = categoryRecyclerViewAdapter.getItem(position);

            intent.putExtra(KEY_CATEGORY_NAME,category.getName()); //Send category name back to NewTransaction activity
            setResult(RESULT_OK,intent);
            finish();
        }

        @Override
        public void onLongClickListener(int position, Category mCategory) {
            showToastMessage("On Long Click");
        }
    };

    @Override
    public void showCategoriesList(List<Category> categoryList) {
        categoryRecyclerViewAdapter.setData(categoryList);
    }
}
