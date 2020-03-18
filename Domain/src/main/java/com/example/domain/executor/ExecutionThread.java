package com.example.domain.executor;

import io.reactivex.rxjava3.core.Scheduler;

/**
 * Created By Viet Hua on 3/18/2020
 */
public interface ExecutionThread {
    Scheduler io();
    Scheduler main();
}
