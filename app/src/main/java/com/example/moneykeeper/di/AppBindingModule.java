package com.example.moneykeeper.di;

import com.example.moneykeeper.presentation.chart.ChartActivity;
import com.example.moneykeeper.presentation.chart.ChartModule;
import com.example.moneykeeper.presentation.home.HomeActivity;
import com.example.moneykeeper.presentation.home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppBindingModule {
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector(modules = ChartModule.class)
    abstract ChartActivity chartActivity();
}
