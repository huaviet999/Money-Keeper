package com.example.data.repository;

import com.example.data.entity.PercentEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public interface PercentDataLocal {
    Maybe<List<PercentEntity>> getSumAndPercent(List<PercentEntity> percentEntityList);

}
