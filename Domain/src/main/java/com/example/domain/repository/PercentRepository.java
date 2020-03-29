package com.example.domain.repository;

import com.example.domain.model.Percent;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

/**
 * Created by Viet Hua on 3/29/2020
 */
public interface PercentRepository {
    Maybe<List<Percent>> getSumAndPercent(List<Percent> percentList);
}
