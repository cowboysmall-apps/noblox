package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Reactor;
import com.cowboysmall.noblox.ReactorFactory;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Runtime.getRuntime;

public class NIOReactorFactory implements ReactorFactory {

    private int slaveCount;


    //_________________________________________________________________________

    public NIOReactorFactory(int slaveCount) {

        this.slaveCount = slaveCount;
    }

    public NIOReactorFactory() {

        this(getRuntime().availableProcessors() - 1);
    }


    //_________________________________________________________________________

    public Reactor createMasterReactor() {

        return new NIOReactor();
    }

    public Queue<Reactor> createSlaveReactors() {

        Queue<Reactor> slaveReactors = new LinkedList<>();

        while (slaveReactors.size() < slaveCount)
            slaveReactors.add(new NIOReactor());

        return slaveReactors;
    }
}
