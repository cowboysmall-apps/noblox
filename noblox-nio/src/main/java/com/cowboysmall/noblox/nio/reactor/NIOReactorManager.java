package com.cowboysmall.noblox.nio.reactor;

import com.cowboysmall.noblox.reactor.AbstractReactorManager;
import com.cowboysmall.noblox.reactor.Reactor;

public class NIOReactorManager extends AbstractReactorManager {

    @Override
    protected Reactor createReactor() {

        return new NIOReactor();
    }
}
