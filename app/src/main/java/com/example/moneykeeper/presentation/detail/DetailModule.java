package com.example.moneykeeper.presentation.detail;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DetailModule {
    @Binds
    public abstract DetailContract.Presenter detailPresenter(DetailPresenterImpl detailPresenter);
}
