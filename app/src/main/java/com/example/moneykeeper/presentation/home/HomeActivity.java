package com.example.moneykeeper.presentation.home;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseActivity;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
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
    @Inject
    HomeContract.Presenter presenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setupToolbar();
       setupNavigationView();
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

    private NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_overview:
                    showToastMessage("Overview");
                    break;
                case R.id.item_summary:
                    showToastMessage("Summary");
                    break;
                case R.id.item_transaction:
                    showToastMessage("Transaction");
                    break;
                case R.id.item_chart:
                    showToastMessage("Chart");
                    break;
                case R.id.item_setting:
                    showToastMessage("Setting");
                    break;
                default:
                    return false;
            }
            drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
    };


}
