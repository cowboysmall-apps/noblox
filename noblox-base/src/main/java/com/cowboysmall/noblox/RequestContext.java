package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.BasicBuffer;
import com.cowboysmall.noblox.buffer.Buffer;


public class RequestContext {

    private final Buffer input = new BasicBuffer();
    private final Buffer output = new BasicBuffer();


    //_________________________________________________________________________

    public Buffer getInput() {

        return input;
    }

    public Buffer getOutput() {

        return output;
    }
}
