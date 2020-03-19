package com.example.domain.interactor.base;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;

/**
 * Created By Viet Hua on 3/18/2020
 */
public abstract class MaybeUsecase<P> {
    ExecutionThread executionThread;
    private CompositeDisposable compositeDisposable;

    public MaybeUsecase(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Maybe buildUseCase(P param);

    protected DisposableMaybeObserver disposableMaybeObserver;

    public void execute(DisposableMaybeObserver disposableMaybeObserver, P param) {
        this.disposableMaybeObserver = disposableMaybeObserver;
        Maybe maybe = buildUseCase(param)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.main());
        addDisposable((Disposable) maybe.subscribeWith(disposableMaybeObserver));
    }

    private void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public void dispose() {
        this.compositeDisposable.dispose();
    }

}
