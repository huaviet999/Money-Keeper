package com.example.domain.interactor.transaction;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.model.Category;
import com.example.domain.model.Percent;
import com.example.domain.repository.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetSumAndPercentUseCase extends MaybeUsecase<List<Percent>> {

    TransactionRepository transactionRepository;

    @Inject
    public GetSumAndPercentUseCase(ExecutionThread executionThread, TransactionRepository transactionRepository){
        super(executionThread);
        this.transactionRepository = transactionRepository;

    }

    @Override
    protected Maybe buildUseCase(List<Percent> param) {
        return transactionRepository.getSumAndPercent(param);
    }
}
