package com.example.moneykeeper.di;

import com.example.moneykeeper.presentation.about.AboutActivity;
import com.example.moneykeeper.presentation.about.AboutModule;
import com.example.moneykeeper.presentation.category.CategoryActivity;
import com.example.moneykeeper.presentation.category.CategoryModule;
import com.example.moneykeeper.presentation.chart.ChartActivity;
import com.example.moneykeeper.presentation.chart.ChartModule;
import com.example.moneykeeper.presentation.detail.DetailActivity;
import com.example.moneykeeper.presentation.detail.DetailModule;
import com.example.moneykeeper.presentation.home.HomeActivity;
import com.example.moneykeeper.presentation.home.HomeModule;
import com.example.moneykeeper.presentation.newtransaction.NewTransactionActivity;
import com.example.moneykeeper.presentation.newtransaction.NewTransactionModule;
import com.example.moneykeeper.presentation.summary.SummaryActivity;
import com.example.moneykeeper.presentation.summary.SummaryModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppBindingModule {
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector(modules = ChartModule.class)
    abstract ChartActivity chartActivity();

    @ContributesAndroidInjector(modules = SummaryModule.class)
    abstract SummaryActivity summaryActivity();

    @ContributesAndroidInjector(modules = CategoryModule.class)
    abstract CategoryActivity addCategoryActivity();

    @ContributesAndroidInjector(modules = NewTransactionModule.class)
    abstract NewTransactionActivity newTransactionActivity();

    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity detailActivity();

    @ContributesAndroidInjector(modules = AboutModule.class)
    abstract AboutActivity aboutActivity();
}
