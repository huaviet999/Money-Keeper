package com.example.moneykeeper.presentation.addcategory;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.domain.model.ExpenseType;
import com.example.domain.model.ModelTest1;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.example.moneykeeper.presentation.base.ItemClickListener;

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
public class AddCategoryActivity extends BaseActivity implements AddCategoryContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;

    public static void startAddCategoryActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, AddCategoryActivity.class);
        activity.startActivity(intent);
    }

    @Inject
    AddCategoryContract.Presenter presenter;

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
        return R.layout.activity_addcategory;
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

    private AddCategoryRecyclerViewAdapter addCategoryRecyclerViewAdapter;

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        addCategoryRecyclerViewAdapter = new AddCategoryRecyclerViewAdapter(this, listener);
        addCategoryRecyclerViewAdapter.setData(testData());
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.setAdapter(addCategoryRecyclerViewAdapter);
    }

    private ItemClickListener<ModelTest1> listener = new ItemClickListener<ModelTest1>() {
        @Override
        public void onClickListener(int position, ModelTest1 modelTest1) {
            showToastMessage("On Click");
        }

        @Override
        public void onLongClickListener(int position, ModelTest1 modelTest1) {
            showToastMessage("On Long Click");
        }
    };

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
