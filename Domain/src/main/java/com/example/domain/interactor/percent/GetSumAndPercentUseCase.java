package com.example.domain.interactor.percent;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUsecase;
import com.example.domain.model.Category;
import com.example.domain.model.Percent;
import com.example.domain.repository.PercentRepository;
import com.example.domain.repository.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

public class GetSumAndPercentUseCase extends MaybeUsecase<List<Percent>> {

    PercentRepository percentRepository;

    @Inject
    public GetSumAndPercentUseCase(ExecutionThread executionThread, PercentRepository percentRepository){
        super(executionThread);
        this.percentRepository = percentRepository;

    }

    @Override
    protected Maybe buildUseCase(List<Percent> param) {
        return percentRepository.getSumAndPercent(param);
    }
}
