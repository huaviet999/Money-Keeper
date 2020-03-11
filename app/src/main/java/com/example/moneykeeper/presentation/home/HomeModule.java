package com.example.moneykeeper.presentation.home;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeModule {
    @Binds
    public abstract HomeContract.Presenter homePresenter(HomePresenterImpl impl);
}
