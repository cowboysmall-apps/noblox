package com.cowboysmall.noblox.thread;

import static java.util.concurrent.Executors.newFixedThreadPool;


public class ThreadCountExecutor extends AbstractExecutor {

    public ThreadCountExecutor(int threadCount) {

        super(newFixedThreadPool(threadCount));
    }
}
