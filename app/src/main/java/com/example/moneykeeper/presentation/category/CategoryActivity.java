package com.example.moneykeeper.presentation.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.domain.model.Category;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;

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
import timber.log.Timber;

/**
 * Created by Viet Hua on 3/15/2020
 */
public class CategoryActivity extends BaseActivity implements CategoryContract.View {
    public static final int CATEGORY_REQUEST_CODE = 100;
    public static final String KEY_CATEGORY_NAME = "KEY_CATEGORY_NAME";
    public static final String KEY_TRANSACTION_TYPE = "KEY_TRANSACTION_TYPE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

    @Inject
    CategoryContract.Presenter presenter;

    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;

    public static void startCategoryActivityForResult(AppCompatActivity activity, String type) {
        Timber.d("startCategoryActivityForResult: %s", type);
        Intent intent = new Intent(activity, CategoryActivity.class);
        intent.putExtra(KEY_TRANSACTION_TYPE, type);
        activity.startActivityForResult(intent, CATEGORY_REQUEST_CODE);
    }


    @Override
    protected int getResLayoutId() {
        return R.layout.activity_category;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        Timber.d("onStart");
        super.onStart();
        presenter.attachView(this);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString(KEY_TRANSACTION_TYPE);
        presenter.getDefaultCategoriesListByType(type);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        presenter.dropView();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionItemSelected: %s", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    @Override
    public void showCategoriesList(List<Category> categoryList) {
        Timber.d("showCategoriesList: %s", categoryList.toString());
        categoryRecyclerViewAdapter.setData(categoryList);
    }


    private void setupViews() {
        Timber.d("setupViews");
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        toolbar.inflateMenu(R.menu.menu_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }


    private void setupRecyclerView() {
        Timber.d("setupRecyclerView");
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(this, listener);
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.setAdapter(categoryRecyclerViewAdapter);

    }

    private ItemClickListener<Category> listener = new ItemClickListener<Category>() {
        @Override
        public void onClickListener(int position, Category mCategory) {
            Timber.d("onCategoryClicked : %d", position);
            Intent intent = new Intent();
            Category category = categoryRecyclerViewAdapter.getItem(position);

            intent.putExtra(KEY_CATEGORY_NAME, category.getName()); //Send category name back to NewTransaction activity
            setResult(RESULT_OK, intent);
            finish();
        }

        @Override
        public void onLongClickListener(int position, Category mCategory) {
            Timber.d("onCategoryLongClicked : %d", position);
            showToastMessage("On Long Click");
        }
    };
}
