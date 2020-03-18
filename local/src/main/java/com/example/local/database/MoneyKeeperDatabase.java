package com.example.local.database;

import android.content.Context;

import com.example.local.database.dao.TransactionDao;
import com.example.local.model.TransactionModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TransactionModel.class}, version = 13)
public abstract class MoneyKeeperDatabase extends RoomDatabase {
    public static MoneyKeeperDatabase instance;

    public abstract TransactionDao transactionDao();

    public static  MoneyKeeperDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MoneyKeeperDatabase.class, "mkp_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
