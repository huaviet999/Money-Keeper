package com.example.domain.interactor.transaction;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetTransactionByTypeUseCase extends MaybeUsecase<String> {
    TransactionRepository transactionRepository;

    @Inject
    public GetTransactionByTypeUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository) {
        super(executionThread);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Maybe buildUseCase(String param) {
        return transactionRepository.getTransactionsByType(param);
    }
}
