package com.cowboysmall.noblox.reactor;

import java.util.Queue;


public interface ReactorFactory {

    Reactor createMasterReactor();

    Queue<Reactor> createSlaveReactors();
}
