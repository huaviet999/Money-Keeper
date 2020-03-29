package com.example.local;

import com.example.data.entity.PercentEntity;
import com.example.data.repository.PercentDataLocal;
import com.example.domain.executor.ExecutionThread;
import com.example.local.database.MoneyKeeperDatabase;
import com.example.local.database.dao.PercentDao;
import com.example.local.database.dao.TransactionDao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;

/**
 * Created by Viet Hua on 3/29/2020
 */
public class PercentDataLocalImpl implements PercentDataLocal {
    private ExecutionThread executionThread;
    private TransactionDao transactionDao;
    private PercentDao percentDao;


    @Inject
    public PercentDataLocalImpl(MoneyKeeperDatabase moneyKeeperDatabase, ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.transactionDao = moneyKeeperDatabase.transactionDao();
        this.percentDao = moneyKeeperDatabase.percentDao();
    }

    @Override
    public Maybe<List<PercentEntity>> getSumAndPercent(final List<PercentEntity> percentEntityList) {
        return Maybe.create(new MaybeOnSubscribe<List<PercentEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<PercentEntity>> emitter) throws Throwable {
                List<PercentEntity> result = new ArrayList<>();
                for (PercentEntity percentEntity : percentEntityList) {
                    long sumByCategory = percentDao.getSumAmountByCategory(percentEntity.getCategoryEntity().getName());
                    long total = percentDao.getSumAmount(percentEntity.getCategoryEntity().getType());
                    float percent = ((float) sumByCategory / total) * 100;
                    percentEntity.setSum(sumByCategory);
                    percentEntity.setPercent(percent);
                    result.add(percentEntity);
                }
                emitter.onSuccess(result);
            }
        });
    }

}
