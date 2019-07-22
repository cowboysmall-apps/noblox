package com.cowboysmall.noblox;

import java.io.IOException;

public interface Acceptor {

    Object getAcceptor();

    Channel accept() throws IOException;
}
