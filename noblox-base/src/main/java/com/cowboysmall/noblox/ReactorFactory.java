package com.cowboysmall.noblox;

import java.util.Queue;


public interface ReactorFactory {

    Reactor createMasterReactor();

    Queue<Reactor> createSlaveReactors();
}
