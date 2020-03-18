package com.example.domain.interactor;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;


import io.reactivex.rxjava3.core.Maybe;

public class GetTransactionDataUseCase extends MaybeUsecase<EmptyParam> {
    TransactionRepository transactionRepository;

    @Inject
    public GetTransactionDataUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository) {
        super(executionThread);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam emptyParam) {
        return transactionRepository.getAllTransactionData();
    }
}
