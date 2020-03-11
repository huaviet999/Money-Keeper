package com.example.moneykeeper.di;

import com.example.moneykeeper.presentation.home.HomeActivity;
import com.example.moneykeeper.presentation.home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppBindingModule {
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
