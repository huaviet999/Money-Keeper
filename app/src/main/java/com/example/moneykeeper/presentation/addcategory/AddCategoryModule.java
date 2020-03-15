package com.example.moneykeeper.presentation.addcategory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AddCategoryModule {
    @Binds
    public abstract AddCategoryContract.Presenter addCategoryPresenter(AddCategoryPresenterImpl presenter);
}
