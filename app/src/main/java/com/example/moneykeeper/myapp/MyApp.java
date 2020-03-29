package com.example.moneykeeper.myapp;

import android.app.Application;

import com.example.moneykeeper.BuildConfig;
import com.example.moneykeeper.di.AppComponent;
import com.example.moneykeeper.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

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
        //Plant Timber
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        //Init Dagger
        initDagger();
    }

    private AppComponent appComponent;

    private void initDagger() {
        Timber.d("initDagger");
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }
}
