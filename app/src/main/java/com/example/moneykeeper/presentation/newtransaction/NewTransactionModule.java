package com.example.moneykeeper.presentation.newtransaction;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewTransactionModule {
    @Binds
    public abstract NewTransactionContract.Presenter newTransactionPresenter(NewTransactionPresenterImpl presenter);
}
