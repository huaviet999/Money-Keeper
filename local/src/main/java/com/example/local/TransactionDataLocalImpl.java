package com.example.local;

import android.util.Log;

import com.example.data.repository.TransactionDataLocal;
import com.example.domain.executor.ExecutionThread;
import com.example.local.database.MoneyKeeperDatabase;
import com.example.local.database.dao.TransactionDao;
import com.example.local.model.TransactionModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionDataLocalImpl implements TransactionDataLocal {

    private ExecutionThread executionThread;
    private TransactionDao transactionDao;

    @Inject
    public TransactionDataLocalImpl(MoneyKeeperDatabase moneyKeeperDatabase, ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.transactionDao = moneyKeeperDatabase.transactionDao();
    }

    @Override
    public Completable saveTransaction(final String type, final String categoryName, final long amount, final long date, final String memo) {
        Log.d("SAVETRANSACTION",type + " " + categoryName + " " + amount + " " + date + " " + memo);
        Log.d("SAVETRANSACTION", "SaveTransaction");
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
}
