package com.example.domain.interactor.transaction;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class DeleteTransactionsDataUseCase extends CompletableUseCase<EmptyParam> {
    TransactionRepository transactionRepository;

    @Inject
    public DeleteTransactionsDataUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository) {
        super(executionThread);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Completable buildUseCase(EmptyParam param) {
        return transactionRepository.deleteAllTransactionData();
    }
}
