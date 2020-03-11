package com.example.moneykeeper.presentation.chart;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ChartModule {
    @Binds
    public abstract ChartContract.Presenter chartPresenter(ChartPresenterImpl presenter);
}
