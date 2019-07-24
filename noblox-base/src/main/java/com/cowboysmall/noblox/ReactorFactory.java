package com.cowboysmall.noblox;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Runtime.getRuntime;

public class ReactorFactory {

    private Class<? extends Reactor> reactorClass;

    private int slaveCount;


    //_________________________________________________________________________

    public ReactorFactory(Class<? extends Reactor> reactorClass, int slaveCount) {

        this.reactorClass = reactorClass;
        this.slaveCount = slaveCount;
    }

    public ReactorFactory(Class<? extends Reactor> reactorClass) {

        this(reactorClass, (getRuntime().availableProcessors() * 2) - 1);
    }

    //_________________________________________________________________________

    public Reactor createMasterReactor() throws Exception {

        return reactorClass.newInstance();
    }

    public Queue<Reactor> createSlaveReactors() throws Exception {

        Queue<Reactor> slaveReactors = new LinkedList<>();

        while (slaveReactors.size() < slaveCount)
            slaveReactors.add(reactorClass.newInstance());

        return slaveReactors;
    }
}
