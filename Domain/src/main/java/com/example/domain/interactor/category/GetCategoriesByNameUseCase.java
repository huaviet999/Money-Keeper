package com.example.domain.interactor.category;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.model.Transaction;
import com.example.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetCategoriesByNameUseCase extends MaybeUsecase<List<Transaction>> {
    CategoryRepository categoryRepository;

    @Inject
    public GetCategoriesByNameUseCase(ExecutionThread executionThread, CategoryRepository categoryRepository) {
        super(executionThread);
        this.categoryRepository = categoryRepository;
    }


    @Override
    protected Maybe buildUseCase(List<Transaction> param) {
        return categoryRepository.getCategoriesByName(param);
    }
}
