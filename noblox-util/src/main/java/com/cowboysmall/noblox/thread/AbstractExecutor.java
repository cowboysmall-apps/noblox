package com.cowboysmall.noblox.thread;

import java.util.concurrent.ExecutorService;

public abstract class AbstractExecutor implements Executor {

    private final ExecutorService executorService;


    //_________________________________________________________________________

    public AbstractExecutor(ExecutorService executorService) {

        this.executorService = executorService;
    }


    //_________________________________________________________________________

    @Override
    public void execute(Runnable runnable) {

        executorService.execute(runnable);
    }
}
