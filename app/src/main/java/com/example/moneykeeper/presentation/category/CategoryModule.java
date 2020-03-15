package com.example.moneykeeper.presentation.category;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CategoryModule {
    @Binds
    public abstract CategoryContract.Presenter addCategoryPresenter(CategoryPresenterImpl presenter);
}
