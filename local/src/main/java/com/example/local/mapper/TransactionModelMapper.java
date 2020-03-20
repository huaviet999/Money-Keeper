package com.example.local.mapper;

import com.example.data.entity.TransactionEntity;
import com.example.local.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionModelMapper implements BaseMapper<TransactionEntity, TransactionModel> {
    @Override
    public TransactionModel mapToModel(TransactionEntity transactionEntity) {
        return null;
    }

    @Override
    public TransactionEntity mapFromModel(TransactionModel transactionModel) {
        if (transactionModel == null) {
            return null;
        }
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transactionModel.getTransactionId());
        transactionEntity.setType(transactionModel.getType());
        transactionEntity.getCategoryEntity().setName(transactionModel.getCategoryName());
        transactionEntity.setAmount(transactionModel.getAmount());
        transactionEntity.setDate(transactionModel.getDate());
        transactionEntity.setMemo(transactionModel.getMemo());
        return transactionEntity;
    }

    public List<TransactionEntity> mapFromModels(List<TransactionModel> transactionModels) {
        List<TransactionEntity> entities = new ArrayList<>();
        for (TransactionModel transactionModel : transactionModels) {
            entities.add(mapFromModel(transactionModel));
        }
        return entities;
    }
}
