package com.example.moneykeeper.di;

import com.example.moneykeeper.presentation.chart.ChartActivity;
import com.example.moneykeeper.presentation.chart.ChartModule;
import com.example.moneykeeper.presentation.home.HomeActivity;
import com.example.moneykeeper.presentation.home.HomeModule;
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
}
