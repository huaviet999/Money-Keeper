package com.example.domain.interactor;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.repository.TransactionRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class SaveTransactionUseCase extends CompletableUseCase<SaveTransactionUseCase.Param> {

    TransactionRepository transactionRepository;

    @Inject
    public SaveTransactionUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository) {
        super(executionThread);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Completable buildUseCase(Param param) {
        return transactionRepository.saveTransaction(param.type,
                param.categoryName, param.amount, param.date, param.memo);
    }

    public static class Param {
        public String type;
        public String categoryName;
        public String memo;
        public long amount;
        public long date;

        public Param(String type, String categoryName, String memo, long amount, long date) {
            this.type = type;
            this.categoryName = categoryName;
            this.memo = memo;
            this.amount = amount;
            this.date = date;
        }
    }
}
