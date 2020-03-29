package com.example.data;

import com.example.data.entity.PercentEntity;
import com.example.data.mapper.PercentEntityMapper;
import com.example.data.mapper.TransactionEntityMapper;
import com.example.data.repository.PercentDataLocal;
import com.example.data.repository.TransactionDataLocal;
import com.example.domain.model.Percent;
import com.example.domain.repository.PercentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Function;

/**
 * Created by Viet Hua on 3/29/2020
 */
public class PercentRepositoryImpl implements PercentRepository {
    private PercentEntityMapper percentEntityMapper;
    PercentDataLocal percentDataLocal;

    @Inject
    public PercentRepositoryImpl(PercentDataLocal percentDataLocal) {
        this.percentDataLocal = percentDataLocal;
        percentEntityMapper = new PercentEntityMapper();
    }
    @Override
    public Maybe<List<Percent>> getSumAndPercent(List<Percent> percentList) {
        return percentDataLocal.getSumAndPercent(percentEntityMapper.mapToEntities(percentList)).map(new Function<List<PercentEntity>, List<Percent>>() {
            @Override
            public List<Percent> apply(List<PercentEntity> percentEntities) throws Throwable {
                return percentEntityMapper.mapFromEntities(percentEntities);
            }
        });
    }
}
