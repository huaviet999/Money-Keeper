package com.example.data.mapper;

import com.example.data.entity.TransactionEntity;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionEntityMapper implements BaseMapper<TransactionEntity, Transaction> {
    @Override
    public TransactionEntity mapToEntity(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction mapFromEntity(TransactionEntity transactionEntity) {
        if (transactionEntity == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setType(transactionEntity.getType());
        transaction.setCategoryName(transactionEntity.getCategoryName());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setDate(transactionEntity.getDate());
        transaction.setMemo(transactionEntity.getMemo());
        return transaction;
    }

    public List<Transaction> mapFromEntities(List<TransactionEntity> transactionEntities) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntities) {
            transactions.add(mapFromEntity(transactionEntity));
        }
        return transactions;
    }

}
