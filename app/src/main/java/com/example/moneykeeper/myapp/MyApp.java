package com.example.moneykeeper.myapp;

import android.app.Application;

import com.example.moneykeeper.di.AppComponent;
import com.example.moneykeeper.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class MyApp extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Init Dagger
        initDagger();
    }

    private AppComponent appComponent;

    private void initDagger() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }
}
