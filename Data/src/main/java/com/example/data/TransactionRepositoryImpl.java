package com.example.data;

import com.example.data.entity.PercentEntity;
import com.example.data.entity.TransactionEntity;
import com.example.data.mapper.PercentEntityMapper;
import com.example.data.mapper.TransactionEntityMapper;
import com.example.data.repository.TransactionDataLocal;
import com.example.domain.model.Percent;
import com.example.domain.model.Transaction;
import com.example.domain.repository.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Function;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    private TransactionEntityMapper transactionEntityMapper;
    private PercentEntityMapper percentEntityMapper;
    TransactionDataLocal transactionDataLocal;

    @Inject
    public TransactionRepositoryImpl(TransactionDataLocal transactionDataLocal) {
        this.transactionDataLocal = transactionDataLocal;
        transactionEntityMapper = new TransactionEntityMapper();
        percentEntityMapper = new PercentEntityMapper();
    }

    @Override
    public Completable saveTransaction(String type, String categoryName, long amount, long date, String memo) {
        return transactionDataLocal.saveTransaction(type, categoryName, amount, date, memo);
    }

    @Override
    public Maybe<List<Transaction>> getAllTransactionData() {
        return transactionDataLocal.getAllTransactionData().map(new Function<List<TransactionEntity>, List<Transaction>>() {
            @Override
            public List<Transaction> apply(List<TransactionEntity> transactionEntities) throws Throwable {
                return transactionEntityMapper.mapFromEntities(transactionEntities);
            }
        });
    }

    @Override
    public Maybe<List<Transaction>> getTransactionsByType(String transactionType) {
        return transactionDataLocal.getTransactionByType(transactionType).map(new Function<List<TransactionEntity>, List<Transaction>>() {
            @Override
            public List<Transaction> apply(List<TransactionEntity> transactionEntities) throws Throwable {
                return transactionEntityMapper.mapFromEntities(transactionEntities);
            }
        });
    }

    @Override
    public Maybe<Transaction> getTransactionById(int transactionId) {
        return transactionDataLocal.getTransactionById(transactionId).map(new Function<TransactionEntity, Transaction>() {
            @Override
            public Transaction apply(TransactionEntity transactionEntity) throws Throwable {
                return transactionEntityMapper.mapFromEntity(transactionEntity);
            }
        });
    }

    @Override
    public Maybe<List<Percent>> getSumAndPercent(List<Percent> percentList) {
        return transactionDataLocal.getSumAndPercent(percentEntityMapper.mapToEntities(percentList)).map(new Function<List<PercentEntity>, List<Percent>>() {
            @Override
            public List<Percent> apply(List<PercentEntity> percentEntities) throws Throwable {
                return percentEntityMapper.mapFromEntities(percentEntities);
            }
        });
    }

    @Override
    public Completable deleteAllTransactionData() {
        return transactionDataLocal.deleteAllTransactionsData();
    }

    @Override
    public Completable deleteTransactionById(int transactionId) {
        return transactionDataLocal.deleteTransactionById(transactionId);
    }
}
