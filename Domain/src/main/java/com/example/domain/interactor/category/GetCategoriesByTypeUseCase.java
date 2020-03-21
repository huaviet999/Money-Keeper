package com.example.domain.interactor.category;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.repository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetCategoriesByTypeUseCase extends MaybeUsecase<String> {
    CategoryRepository categoryRepository;

    @Inject
    public GetCategoriesByTypeUseCase(ExecutionThread executionThread, CategoryRepository categoryRepository) {
        super(executionThread);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Maybe buildUseCase(String param) {
        return categoryRepository.getCategoriesByType(param);
    }
}
