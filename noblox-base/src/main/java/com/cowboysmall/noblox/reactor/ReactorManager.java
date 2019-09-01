package com.cowboysmall.noblox.reactor;


import java.util.Queue;

public interface ReactorManager {

    Reactor getMaster();

    Queue<Reactor> getSlaves();

    Reactor getNextSlave();
}
