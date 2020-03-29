package com.example.local;


import com.example.data.entity.PercentEntity;
import com.example.data.entity.TransactionEntity;
import com.example.data.repository.TransactionDataLocal;
import com.example.domain.executor.ExecutionThread;
import com.example.local.database.MoneyKeeperDatabase;
import com.example.local.database.dao.PercentDao;
import com.example.local.database.dao.TransactionDao;
import com.example.local.mapper.TransactionModelMapper;
import com.example.local.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionDataLocalImpl implements TransactionDataLocal {

    private ExecutionThread executionThread;
    private TransactionDao transactionDao;
    private PercentDao percentDao;
    private TransactionModelMapper transactionModelMapper;


    @Inject
    public TransactionDataLocalImpl(MoneyKeeperDatabase moneyKeeperDatabase, ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.transactionDao = moneyKeeperDatabase.transactionDao();
        this.percentDao = moneyKeeperDatabase.percentDao();
        this.transactionModelMapper = new TransactionModelMapper();
    }

    @Override
    public Completable saveTransaction(final String type, final String categoryName, final long amount, final long date, final String memo) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setType(type);
                transactionModel.setCategoryName(categoryName);
                transactionModel.setAmount(amount);
                transactionModel.setDate(date);
                transactionModel.setMemo(memo);
                transactionDao.insert(transactionModel);
                emitter.onComplete();
            }
        }).subscribeOn(executionThread.io());
    }

    @Override
    public Maybe<List<TransactionEntity>> getAllTransactionData() {
        return Maybe.create(new MaybeOnSubscribe<List<TransactionEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<TransactionEntity>> emitter) throws Throwable {
                List<TransactionModel> transactionModels = transactionDao.getAllTransactionData();
                emitter.onSuccess(transactionModelMapper.mapFromModels(transactionModels));
            }
        }).subscribeOn(executionThread.io());
    }

    @Override
    public Maybe<List<TransactionEntity>> getTransactionByType(final String transactionType) {
        return Maybe.create(new MaybeOnSubscribe<List<TransactionEntity>>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<List<TransactionEntity>> emitter) throws Throwable {
                List<TransactionModel> transactionModelList = transactionDao.getTransactionByType(transactionType);
                emitter.onSuccess(transactionModelMapper.mapFromModels(transactionModelList));
            }
        });
    }

    @Override
    public Maybe<TransactionEntity> getTransactionById(final int transactionId) {
        return Maybe.create(new MaybeOnSubscribe<TransactionEntity>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<TransactionEntity> emitter) throws Throwable {
                TransactionModel transactionModel = transactionDao.getTransactionById(transactionId);
                emitter.onSuccess(transactionModelMapper.mapFromModel(transactionModel));
            }
        });
    }


    @Override
    public Completable deleteAllTransactionsData() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                transactionDao.deleteAllTransactionData();
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable deleteTransactionById(final int transactionId) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                transactionDao.deleteTransactionById(transactionId);
                emitter.onComplete();
            }
        });
    }
}
