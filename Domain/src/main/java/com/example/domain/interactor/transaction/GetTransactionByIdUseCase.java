package com.example.domain.interactor.transaction;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetTransactionByIdUseCase extends MaybeUsecase<Integer> {

    TransactionRepository transactionRepository;

    @Inject
    public GetTransactionByIdUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository){
        super(executionThread);
        this.transactionRepository = transactionRepository;

    }

    @Override
    protected Maybe buildUseCase(Integer param) {
        return transactionRepository.getTransactionById(param);
    }
}
