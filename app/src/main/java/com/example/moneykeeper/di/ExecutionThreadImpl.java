package com.example.moneykeeper.di;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ExecutionThreadImpl implements ExecutionThread {
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }
}
