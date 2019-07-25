package com.cowboysmall.noblox.thread;

public class CurrentThreadExecutor implements Executor {

    @Override
    public void execute(Runnable runnable) {

        runnable.run();
    }
}
