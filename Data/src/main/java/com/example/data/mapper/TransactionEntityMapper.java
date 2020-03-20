package com.example.data.mapper;

import com.example.data.entity.TransactionEntity;
import com.example.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viet Hua on 3/18/2020
 */
public class TransactionEntityMapper implements BaseMapper<TransactionEntity, Transaction> {
    CategoryEntityMapper categoryEntityMapper;

    public TransactionEntityMapper() {
        categoryEntityMapper = new CategoryEntityMapper();
    }

    @Override
    public TransactionEntity mapToEntity(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transaction.getTransactionId());
        transactionEntity.setCategoryEntity(categoryEntityMapper.mapToEntity(transaction.getCategory()));
        transactionEntity.setMemo(transaction.getMemo());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setType(transaction.getType());
        return transactionEntity;
    }
    public List<TransactionEntity> mapToEntities(List<Transaction> transactions) {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionEntities.add(mapToEntity(transaction));
        }
        return transactionEntities;
    }

    @Override
    public Transaction mapFromEntity(TransactionEntity transactionEntity) {
        if (transactionEntity == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionEntity.getTransactionId());
        transaction.setType(transactionEntity.getType());
        transaction.setCategory(categoryEntityMapper.mapFromEntity(transactionEntity.getCategoryEntity()));
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
