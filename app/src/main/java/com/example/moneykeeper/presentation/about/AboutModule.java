package com.example.moneykeeper.presentation.about;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AboutModule {
    @Binds
    public abstract AboutContract.Presenter aboutPresenter(AboutPresenterImpl impl);
}
