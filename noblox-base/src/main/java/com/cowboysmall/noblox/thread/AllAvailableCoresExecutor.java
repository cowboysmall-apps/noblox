package com.cowboysmall.noblox.thread;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class AllAvailableCoresExecutor extends AbstractExecutor {

    public AllAvailableCoresExecutor() {

        super(newFixedThreadPool(getRuntime().availableProcessors()));
    }
}
