package com.example.local.database;

import android.content.Context;
import android.util.Log;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.model.Category;
import com.example.local.database.dao.CategoryDao;
import com.example.local.database.dao.PercentDao;
import com.example.local.database.dao.TransactionDao;
import com.example.local.model.CategoryModel;
import com.example.local.model.TransactionModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Database(entities = {TransactionModel.class, CategoryModel.class}, version = 13)
public abstract class MoneyKeeperDatabase extends RoomDatabase {
    public static MoneyKeeperDatabase instance;

    public abstract TransactionDao transactionDao();

    public abstract CategoryDao categoryDao();

    public abstract PercentDao percentDao();

    public static MoneyKeeperDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MoneyKeeperDatabase.class, "mkp_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            final CategoryDao categoryDao = instance.categoryDao();
            Completable completable = Completable.create(new CompletableOnSubscribe() {
                @Override
                public void subscribe(@io.reactivex.rxjava3.annotations.NonNull CompletableEmitter emitter) throws Throwable {
                    //Add Default Category Data
                    categoryDao.insert(new CategoryModel("Food", "nfood", "cfood", "Expense","#00A591"));
                    categoryDao.insert(new CategoryModel("Transport", "ntransport", "ctransport", "Expense","#DC4C46"));
                    categoryDao.insert(new CategoryModel("Shopping", "nshopping", "cshopping", "Expense","#95DEE3"));
                    categoryDao.insert(new CategoryModel("Bills", "nbill", "cbill", "Expense","#004B8D"));
                    categoryDao.insert(new CategoryModel("Health", "nhealth", "chealth", "Expense","#CE3175"));
                    categoryDao.insert(new CategoryModel("Telephone", "nphone", "cphone", "Expense","#006E51"));
                    categoryDao.insert(new CategoryModel("Home", "nhome", "chome", "Expense","#F7786B"));
                    categoryDao.insert(new CategoryModel("Education", "neducation", "ceducation", "Expense","#91A8D0"));
                    categoryDao.insert(new CategoryModel("Travel", "ntravel", "ctravel", "Expense","#DD4132"));
                    categoryDao.insert(new CategoryModel("Insurance", "ninsurance", "cinsurance", "Expense","#9B1B30"));
                    categoryDao.insert(new CategoryModel("Social", "nsocial", "csocial", "Expense","#FA9A85"));
                    categoryDao.insert(new CategoryModel("Sport", "nsport", "csport", "Expense","#CE5B78"));
                    categoryDao.insert(new CategoryModel("Gift", "ngift", "cgift", "Expense","#F96714"));
                    categoryDao.insert(new CategoryModel("Others", "nother", "cother", "Expense","#EC9787"));

                    categoryDao.insert(new CategoryModel("Salary", "nsalary", "csalary", "Income","#FF6F61"));
                    categoryDao.insert(new CategoryModel("Awards", "naward", "caward", "Income","#009B77"));
                    categoryDao.insert(new CategoryModel("Grants", "ngrant", "cgrant", "Income","#B565A7"));
                    categoryDao.insert(new CategoryModel("Sale", "nsale", "csale", "Income","#D65076"));
                    categoryDao.insert(new CategoryModel("Rental", "nrental", "crental", "Income","#45B8AC"));
                    categoryDao.insert(new CategoryModel("Coupons", "ncoupon", "ccoupon", "Income","#C3447A"));
                    categoryDao.insert(new CategoryModel("Lottery", "nlottery", "clottery", "Income","#EFC050"));
                    categoryDao.insert(new CategoryModel("Dividend", "ndividend", "cdividend", "Income","#C62168"));
                    categoryDao.insert(new CategoryModel("Invest", "ninvestment", "cinvestment", "Income","#E94B3C"));
                    categoryDao.insert(new CategoryModel("Others", "nother", "cother", "Income","#EC9787"));

                    emitter.onComplete();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            compositeDisposable.add(completable.subscribeWith(new InsertCategoryObserver()));
        }
    };

    private static class InsertCategoryObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Log.d("CATEGORY", "DEFAULT DATA SUCCEED");
        }

        @Override
        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

        }
    }


}
