package com.example.domain.interactor.base;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;

/**
 * Created by Viet Hua on 3/18/2020
 */
public abstract class CompletableUseCase<P> {
    ExecutionThread executionThread;
    private CompositeDisposable compositeDisposable;

    public CompletableUseCase(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Completable buildUseCase(P param);

    protected DisposableCompletableObserver disposableCompletableObserver;

    public void execute(DisposableCompletableObserver disposableCompletableObserver, P param) {
        this.disposableCompletableObserver = disposableCompletableObserver;
        Completable completable = buildUseCase(param)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.main());
        addDisposable(completable.subscribeWith(disposableCompletableObserver));

    }

    private void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public void dispose() {
        this.compositeDisposable.dispose();
    }
}
