package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.OutputBuffer;

public class Context {

    private OutputBuffer outputBuffer;

    private byte[] bytesRead;


    //_________________________________________________________________________

    public Context(byte[] bytesRead, OutputBuffer outputBuffer) {

        this.bytesRead = bytesRead;
        this.outputBuffer = outputBuffer;
    }


    //_________________________________________________________________________

    public OutputBuffer getOutputBuffer() {

        return outputBuffer;
    }

    public byte[] getBytesRead() {

        return bytesRead;
    }
}
