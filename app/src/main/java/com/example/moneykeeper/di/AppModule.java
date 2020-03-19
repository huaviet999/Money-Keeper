package com.example.moneykeeper.di;

import android.content.Context;

import com.example.domain.executor.ExecutionThread;
import com.example.local.database.MoneyKeeperDatabase;
import com.example.moneykeeper.myapp.MyApp;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(MyApp myApp){return myApp;}

    @Provides
    @Singleton
    public ExecutionThread provideExecutionThread(){
        return new ExecutionThreadImpl();
    }

    @Provides
    @Singleton
    public MoneyKeeperDatabase provideMoneyKeeperDB(Context context,ExecutionThread executionThread){
        return MoneyKeeperDatabase.getInstance(context);
    }
}
