package com.example.moneykeeper.presentation.summary;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SummaryModule {
    @Binds
    public abstract SummaryContract.Presenter summaryPresenter(SummaryPresenterImpl presenter);
}
