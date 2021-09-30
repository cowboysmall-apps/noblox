package com.cowboysmall.noblox.reactor;

import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Runtime.getRuntime;
import static java.util.Comparator.comparing;

public abstract class AbstractReactorManager implements ReactorManager {

    private final Reactor master;
    private final Queue<Reactor> slaves = new PriorityQueue<>(comparing(Reactor::activeConnections));


    //_________________________________________________________________________

    public AbstractReactorManager(int slaveCount) {

        master = createReactor();
        while (slaves.size() < slaveCount)
            slaves.add(createReactor());
    }

    public AbstractReactorManager() {

        this(getRuntime().availableProcessors() - 1);
    }


    //_________________________________________________________________________

    @Override
    public Reactor getMaster() {

        return master;
    }

    @Override
    public Queue<Reactor> getSlaves() {

        return slaves;
    }

    @Override
    public Reactor getNextSlave() {

        Reactor next = slaves.remove();
        slaves.add(next);
        return next;
    }


    //_________________________________________________________________________

    protected abstract Reactor createReactor();
}
