package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.Buffer;


public class RequestContext {

    private Buffer input;
    private Buffer output;


    //_________________________________________________________________________

    public RequestContext(Buffer input, Buffer output) {

        this.input = input;
        this.output = output;
    }


    //_________________________________________________________________________

    public Buffer getInput() {

        return input;
    }

    public Buffer getOutput() {

        return output;
    }
}
