package com.cowboysmall.noblox.nio.reactor;

import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.ReactorFactory;

import java.util.Comparator;
import java.util.PriorityQueue;
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

        Queue<Reactor> slaveReactors =
                new PriorityQueue<>(Comparator.comparing(Reactor::activeConnections));

        while (slaveReactors.size() < slaveCount)
            slaveReactors.add(new NIOReactor());

        return slaveReactors;
    }
}
