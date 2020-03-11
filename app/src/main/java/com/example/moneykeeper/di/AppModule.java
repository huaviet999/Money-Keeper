package com.example.moneykeeper.di;

import android.content.Context;

import com.example.moneykeeper.myapp.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(MyApp myApp){return myApp;}


}
