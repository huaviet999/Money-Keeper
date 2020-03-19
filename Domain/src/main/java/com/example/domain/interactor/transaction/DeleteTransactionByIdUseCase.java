package com.example.domain.interactor.transaction;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class DeleteTransactionByIdUseCase extends CompletableUseCase<Integer> {
    TransactionRepository transactionRepository;

    @Inject
    public DeleteTransactionByIdUseCase(ExecutionThread executionThread, TransactionRepository repository) {
        super(executionThread);
        this.transactionRepository = repository;
    }

    @Override
    protected Completable buildUseCase(Integer param) {
        return transactionRepository.deleteTransactionById(param);
    }
}
