package com.cowboysmall.noblox.thread;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class ThreadPoolExecutor extends AbstractExecutor {

    public ThreadPoolExecutor() {

        super(newFixedThreadPool(getRuntime().availableProcessors()));
    }

    public ThreadPoolExecutor(int threadCount) {

        super(newFixedThreadPool(threadCount));
    }
}
