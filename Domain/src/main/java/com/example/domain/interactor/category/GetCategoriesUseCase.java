package com.example.domain.interactor.category;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetCategoriesUseCase extends MaybeUsecase<EmptyParam> {
    private CategoryRepository categoryRepository;

    @Inject
    public GetCategoriesUseCase(ExecutionThread executionThread, CategoryRepository categoryRepository) {
        super(executionThread);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return categoryRepository.getAllCategories();
    }
}
