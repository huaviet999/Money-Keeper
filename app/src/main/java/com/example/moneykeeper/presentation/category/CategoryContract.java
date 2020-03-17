package com.example.moneykeeper.presentation.category;

import com.example.domain.model.Category;
import com.example.moneykeeper.presentation.base.BasePresenter;
import com.example.moneykeeper.presentation.base.BaseView;

import java.util.List;

public interface CategoryContract {
    interface View extends BaseView {
        void showCategoriesList(List<Category> categoryList);
    }

    interface Presenter extends BasePresenter<View> {
        void setDefaultCategoriesList();
    }
}
